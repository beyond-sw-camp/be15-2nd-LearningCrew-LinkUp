package com.learningcrew.linkup.meeting.command.application.service;

import com.learningcrew.linkup.common.query.mapper.SportTypeMapper;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.meeting.command.application.dto.request.LeaderUpdateRequest;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingCreateRequest;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingParticipationCreateRequest;
import com.learningcrew.linkup.meeting.command.domain.aggregate.Meeting;
import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingParticipationHistory;
import com.learningcrew.linkup.meeting.command.domain.repository.MeetingParticipationHistoryRepository;
import com.learningcrew.linkup.meeting.command.domain.repository.MeetingRepository;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingDTO;
import com.learningcrew.linkup.meeting.query.dto.response.MemberDTO;
import com.learningcrew.linkup.meeting.query.mapper.MeetingMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor // 의존성 주입
public class MeetingCommandService {
    static final int MINIMUM_OF_MIN_USER = 1;
    static final int MAXIMUM_OF_MAX_USER = 30;

    private final MeetingRepository meetingRepository;
    private final MeetingParticipationHistoryRepository participationRepository;
    private final ModelMapper modelMapper;

    private final MeetingParticipationCommandService commandService;
    private final MeetingParticipationCommandService participationCommandService;
    private final SportTypeMapper sportTypeMapper;

    /* 모임 등록 */
    @Transactional
    public int createMeeting(MeetingCreateRequest meetingCreateRequest) {
        /* 1. 검증 로직 */
        LocalDate meetingDate = meetingCreateRequest.getDate();
        LocalDate now = LocalDate.now();

        // 2주 이내
        if (meetingDate.isBefore(now) || meetingDate.isEqual(now.plusDays(15))) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        // 30분 단위
        if (meetingCreateRequest.getStartTime().getMinute() % 30 != 0 || meetingCreateRequest.getEndTime().getMinute() % 30 != 0) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        // 최대 인원, 최소 인원
        int minUser = meetingCreateRequest.getMinUser();
        int maxUser = meetingCreateRequest.getMaxUser();

        if (maxUser < minUser) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        if (minUser < MINIMUM_OF_MIN_USER || maxUser > MAXIMUM_OF_MAX_USER) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        /* 2. 모임 저장 */
        Meeting meeting = modelMapper.map(meetingCreateRequest, Meeting.class);
        meeting.setStatusId(statusQueryService.getStatusId("PENDING"));

        meetingRepository.save(meeting);
        meetingRepository.flush();

        Meeting savedMeeting = meetingRepository.findById(meeting.getMeetingId()).orElseThrow(() -> new BusinessException(ErrorCode.MEETING_NOT_FOUND));


        /* 3. 개설자를 모임 참가자에 등록 */
        int leaderId = meetingCreateRequest.getLeaderId();

        MeetingParticipationCreateRequest request
                = MeetingParticipationCreateRequest.builder()
                .memberId(leaderId)
                .build();

        participationCommandService.createMeetingParticipation(request, savedMeeting);

        return meeting.getMeetingId();
    }

    /* 개설자 변경 */
    @Transactional
    public int updateLeader(int meetingId, int memberId, LeaderUpdateRequest leaderUpdateRequest) {
        // 1. 모임 정보 조회
        MeetingDTO meeting = meetingMapper.selectMeetingById(meetingId);

        // 2. 권한 체크 (현재 리더가 요청한 사람인지 확인)
        if (meeting.getLeaderId() != leaderUpdateRequest.getMemberId()) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        // 3. 새로운 개설자가 모임 참여자인지 확인
        List<MemberDTO> participants = participationMapper.selectParticipantsByMeetingId(meetingId);
        if (participants.stream().noneMatch(p -> p.getMemberId() == memberId)) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "모임에 속하지 않은 회원입니다.");
        }

        // 4. 기존 개설자의 참여 내역 soft delete
        MeetingParticipationDTO requestedParticipation = participationMapper.selectHistoryByMeetingIdAndMemberId(meetingId, leaderUpdateRequest.getMemberId());

        MeetingParticipationHistory history = modelMapper.map(requestedParticipation, MeetingParticipationHistory.class);
        history.setStatusId(statusQueryService.getStatusId("DELETED")); // soft delete
        participationRepository.save(history);
        requestedParticipation.setStatusType("참가 취소");

        // 5. 모임 리더 변경
        meeting.setLeaderId(memberId);
        Meeting meetingEntity = modelMapper.map(meeting, Meeting.class);

        int sportId = sportTypeMapper.findBySportName(meeting.getSportName())
                .orElseThrow(() -> new BusinessException(ErrorCode.BAD_REQUEST))
                .getSportTypeId();
        int statusId = statusQueryService.getStatusId("DELETED");

        meetingEntity.setSportId(sportId);
        meetingEntity.setStatusId(statusId);
        meetingRepository.save(meetingEntity);

        return meetingId;
    }

    /* 모임 삭제 */
    @Transactional
    public void deleteMeeting(int meetingId) {

        for (String string : List.of("PENDING", "ACCEPTED", "REJECTED")) { // history에서 status를 모두 DELETED로 변경
            List<MeetingParticipationDTO> participants = participationQueryService.getHistories(
                    meetingId, statusQueryService.getStatusId(string)
            );

            participants.forEach(commandService::deleteMeetingParticipation);
        }

        /* DTO와 Entity 간 필요한 변환 로직 */
        MeetingDTO meeting = meetingMapper.selectMeetingById(meetingId);
        meeting.setStatusType("모임 취소");

        Meeting meetingEntity = modelMapper.map(meeting, Meeting.class);
        meetingEntity.setSportId(
                sportTypeMapper.findBySportName(meeting.getSportName())
                        .orElseThrow(() -> new BusinessException(ErrorCode.BAD_REQUEST))
                        .getSportTypeId()
        );
        /* 모임의 status를 deleted로 변경하고 update */
        meetingEntity.setStatusId(statusQueryService.getStatusId("DELETED"));
        meetingRepository.save(meetingEntity);
    }
}

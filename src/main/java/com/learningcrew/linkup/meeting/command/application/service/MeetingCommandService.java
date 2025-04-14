package com.learningcrew.linkup.meeting.command.application.service;

import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingCreateRequest;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingDeleteRequest;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingParticipationDeleteRequest;
import com.learningcrew.linkup.meeting.command.domain.aggregate.Meeting;
import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingParticipationHistory;
import com.learningcrew.linkup.meeting.command.domain.repository.MeetingParticipationHistoryRepository;
import com.learningcrew.linkup.meeting.command.domain.repository.MeetingRepository;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingDTO;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingParticipationDTO;
import com.learningcrew.linkup.meeting.query.dto.response.MemberDTO;
import com.learningcrew.linkup.meeting.query.mapper.MeetingMapper;
import com.learningcrew.linkup.meeting.query.mapper.MeetingParticipationMapper;
import com.learningcrew.linkup.meeting.query.service.MeetingParticipationQueryService;
import com.learningcrew.linkup.meeting.query.service.MeetingQueryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 의존성 주입
public class MeetingCommandService {

    private final MeetingRepository meetingRepository;
    private final MeetingParticipationHistoryRepository participationRepository;
    private final ModelMapper modelMapper;
    private final MeetingMapper meetingMapper;
    private final MeetingParticipationMapper participationMapper;

    private final MeetingParticipationCommandService commandService;
    private final MeetingQueryService meetingQueryService;
    private final MeetingParticipationQueryService meetingParticipationQueryService;

    /* 모임 등록 */
    @Transactional
    public int createMeeting(MeetingCreateRequest meetingCreateRequest) {
        Meeting meeting = modelMapper.map(meetingCreateRequest, Meeting.class);

        meetingRepository.save(meeting);

        return meeting.getMeetingId();
    }

    /* 주최자 변경 */
    @Transactional
    public int updateLeader(int meetingId, int memberId, int requestedMemberId) {
        // 1. 모임 정보 조회
        MeetingDTO meeting = meetingMapper.selectMeetingById(meetingId);

        // 2. 권한 체크 (현재 리더가 요청한 사람인지 확인)
        if (meeting.getLeaderId() != requestedMemberId) {
            // TODO: 커스텀 예외
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        // 3. 새로운 주최자가 모임 참여자인지 확인
        List<MemberDTO> participants = participationMapper.selectParticipantsByMeetingId(meetingId);
        if (participants.stream().noneMatch(p -> p.getMemberId() == memberId)) {
            throw new RuntimeException("모임에 속하지 않은 회원입니다.");
        }

        // 4. 기존 주최자의 참여 내역 soft delete
        MeetingParticipationDTO requestedParticipation = participationMapper.selectHistoryByMeetingIdAndMemberId(meetingId, requestedMemberId);
        requestedParticipation.setStatusId(4); // soft delete
        participationRepository.save(modelMapper.map(requestedParticipation, MeetingParticipationHistory.class));

        // 5. 모임 리더 변경
        meeting.setLeaderId(memberId);
        meetingRepository.save(modelMapper.map(meeting, Meeting.class));

        return meetingId;
    }

    /* 모임 삭제 */
    @Transactional
    public void deleteMeeting(int meetingId) {
        for (int i : new int[]{1, 2, 3}) { // history에서 status를 모두 DELETED로 변경
            List<MeetingParticipationDTO> participants = meetingParticipationQueryService.getHistories(meetingId, i).getMeetingParticipations();

            participants.forEach(
                    participation -> {
                        MeetingParticipationDeleteRequest deleteRequest
                                = new MeetingParticipationDeleteRequest(participation.getMemberId(), meetingId, 4);

                        commandService.deleteMeetingParticipation(participation);
                    }
            );
        }

        MeetingDTO meeting = meetingMapper.selectMeetingById(meetingId);
        meeting.setStatusId(4);
        meetingRepository.save(modelMapper.map(meeting, Meeting.class));
    }
}

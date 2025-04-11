package com.learningcrew.linkup.meeting.command.application.service;

import com.learningcrew.linkup.common.query.mapper.StatusMapper;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingParticipationCreateRequest;
import com.learningcrew.linkup.meeting.command.domain.aggregate.Meeting;
import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingParticipationHistory;
import com.learningcrew.linkup.meeting.command.domain.repository.MeetingParticipationHistoryRepository;
import com.learningcrew.linkup.meeting.command.infrastructure.repository.JpaMeetingParticipationHistoryRepository;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingParticipationDTO;
import com.learningcrew.linkup.meeting.query.mapper.MeetingParticipationMapper;
import com.learningcrew.linkup.meeting.query.service.MeetingParticipationQueryService;
import com.learningcrew.linkup.meeting.query.service.MeetingQueryService;
import com.learningcrew.linkup.meeting.query.service.StatusQueryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 의존성 주입
public class MeetingParticipationCommandService {

    private final MeetingParticipationHistoryRepository repository;
    private final MeetingParticipationMapper mapper;
    private final MeetingQueryService meetingQueryService;
    private final ModelMapper modelMapper;
    private final MeetingParticipationQueryService meetingParticipationQueryService;
    private final StatusQueryService statusQueryService;
    private final JpaMeetingParticipationHistoryRepository jpaRepository;
    private StatusMapper statusMapper;

    /* 모임 등록 */
    @Transactional
    public long createMeetingParticipation(MeetingParticipationCreateRequest request, Meeting meeting) {
        MeetingParticipationHistory history
                = modelMapper.map(request, MeetingParticipationHistory.class);

        /* 개설 요청자와 모임 주최자가 일치하는지 체크 */
        if (meeting.getLeaderId() != request.getLeaderId()) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        /* 회원이 모임에 속해 있는지 확인 */

        List<Integer> participantsIds = jpaRepository.findByMeetingIdAndStatusId(
                meeting.getMeetingId(), statusQueryService.getStatusId("ACCEPTED")
        ).stream().map(MeetingParticipationHistory::getMemberId).toList();

        if (participantsIds.contains(request.getMemberId())) {
            throw new BusinessException(ErrorCode.MEETING_ALREADY_JOINED);
        }

        /* 개설 요청자가 주최자이면 ACCEPTED, 아니면 PENDING 처리 */
        int statusId;
        if (meeting.getLeaderId() != request.getMemberId()) {
            statusId = statusQueryService.getStatusId("PENDING");
        } else {
            statusId = statusQueryService.getStatusId("ACCEPTED");
        }

        history.setStatusId(statusId);

        repository.save(history);

        return history.getParticipationId();
    }

    @Transactional
    public long acceptParticipation(int meetingId, int memberId) {
        MeetingParticipationDTO participation = mapper.selectHistoryByMeetingIdAndMemberId(meetingId, memberId);

        participation.setStatusId(2);

        repository.save(modelMapper.map(participation, MeetingParticipationHistory.class));

        return participation.getParticipationId();
    }

    @Transactional
    public long rejectParticipation(int meetingId, int memberId) {
        MeetingParticipationDTO participation = mapper.selectHistoryByMeetingIdAndMemberId(meetingId, memberId);

        participation.setStatusId(3);

        repository.save(modelMapper.map(participation, MeetingParticipationHistory.class));

        return participation.getParticipationId();
    }

    @Transactional
    public long deleteMeetingParticipation(MeetingParticipationDTO history) {
//        if (history == null) {
//            throw new NotFoundException("참여 정보가 없습니다.");
//        }

        history.setStatusId(4); // soft delete

        repository.save(modelMapper.map(history, MeetingParticipationHistory.class));

        return history.getParticipationId();
    }

}



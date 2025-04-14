package com.learningcrew.linkup.meeting.command.application.service;

import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingParticipationDeleteRequest;
import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingParticipationHistory;
import com.learningcrew.linkup.meeting.command.domain.repository.MeetingParticipationHistoryRepository;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingParticipationCreateRequest;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingParticipationDTO;
import com.learningcrew.linkup.meeting.query.mapper.MeetingParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 의존성 주입
public class MeetingParticipationCommandService {

    private final MeetingParticipationHistoryRepository repository;
    private final MeetingParticipationMapper mapper;
    private final ModelMapper modelMapper;

    /* 모임 등록 */
    @Transactional
    public long createMeetingParticipation(MeetingParticipationCreateRequest request) {
        MeetingParticipationHistory history = modelMapper.map(request, MeetingParticipationHistory.class);

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

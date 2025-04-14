package com.learningcrew.linkup.meeting.query.service;

import com.learningcrew.linkup.meeting.query.dto.response.*;
import com.learningcrew.linkup.meeting.query.mapper.MeetingParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingParticipationQueryService {

    private final MeetingParticipationMapper mapper;

    /* 모임에 속한 참가자 전체 조회 */
    @Transactional(readOnly = true)
    public ParticipantsResponse getParticipants(int meetingId) {
        List<MemberDTO> response = mapper.selectParticipantsByMeetingId(meetingId);

        return ParticipantsResponse.builder()
                .participants(response)
                .build();
    }

    /* 모임에 속한 참가 내역 status별 조회 */
    @Transactional(readOnly = true)
    public MeetingParticipationListResponse getHistories(int meetingId, int statusId) {
        List<MeetingParticipationDTO> response = mapper.selectHistoryByMeetingIdAndStatusId(meetingId, statusId);

        return MeetingParticipationListResponse.builder()
                .meetingParticipations(response)
                .build();
    }


    @Transactional(readOnly = true)
    public ParticipantsResponse getParticipants(int meetingId, int memberId) {
        List<MemberDTO> response = mapper.selectParticipantsByMeetingId(meetingId);
        MeetingParticipationDTO participation = mapper.selectMeetingParticipationByMeetingIdAndMemberId(meetingId, memberId);

        // TODO: 커스텀 예외로 변경
//        if (participation == null) {
//            throw new AccessDeniedException("해당 모임에 참여하지 않은 회원입니다.");
//        }

        return ParticipantsResponse.builder()
                .participants(response)
                .build();
    }

    @Transactional(readOnly = true)
    public MeetingParticipationResponse getParticipation(int meetingId, int memberId) {
        MeetingParticipationDTO response = mapper.selectMeetingParticipationByMeetingIdAndMemberId(meetingId, memberId);

        return MeetingParticipationResponse.builder()
                .participationId(response.getParticipationId())
                .build();
    }
}

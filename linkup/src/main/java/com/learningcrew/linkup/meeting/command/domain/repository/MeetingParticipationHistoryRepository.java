package com.learningcrew.linkup.meeting.command.domain.repository;

import com.learningcrew.linkup.linker.command.domain.aggregate.Member;
import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingParticipationHistory;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingParticipationDTO;

import java.util.List;

public interface MeetingParticipationHistoryRepository {
    MeetingParticipationHistory save(MeetingParticipationHistory history);

    void delete(MeetingParticipationHistory history);

    List<MeetingParticipationHistory> findByMeetingIdAndStatusId(int meetingId, int statusId);

    void flush();

    MeetingParticipationHistory findByMeetingIdAndMemberId(int meetingId, int memberId);

    List<MeetingParticipationHistory> findAllByMeetingIdAndStatusId(int meetingId, int accepted);
}

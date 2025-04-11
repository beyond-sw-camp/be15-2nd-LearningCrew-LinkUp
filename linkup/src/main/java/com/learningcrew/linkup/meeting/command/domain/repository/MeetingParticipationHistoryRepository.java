package com.learningcrew.linkup.meeting.command.domain.repository;

import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingParticipationHistory;

import java.util.Collection;
import java.util.List;

public interface MeetingParticipationHistoryRepository {
    MeetingParticipationHistory save(MeetingParticipationHistory history);

    void delete(MeetingParticipationHistory history);

    List<MeetingParticipationHistory> findByMeetingIdAndStatusId(int meetingId, int statusId);
}

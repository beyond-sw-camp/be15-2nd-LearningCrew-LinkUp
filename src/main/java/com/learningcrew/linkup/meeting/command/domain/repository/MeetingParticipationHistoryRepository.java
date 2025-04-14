package com.learningcrew.linkup.meeting.command.domain.repository;

import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingParticipationHistory;

public interface MeetingParticipationHistoryRepository {
    MeetingParticipationHistory save(MeetingParticipationHistory history);

    void delete(MeetingParticipationHistory history);
}

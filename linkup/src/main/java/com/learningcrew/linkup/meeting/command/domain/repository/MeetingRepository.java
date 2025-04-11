package com.learningcrew.linkup.meeting.command.domain.repository;

import com.learningcrew.linkup.meeting.command.domain.aggregate.Meeting;
import java.util.Optional;

public interface MeetingRepository {
    Meeting save(Meeting meeting);

    Optional<Meeting> findById(int meetingId);

    void flush();
}

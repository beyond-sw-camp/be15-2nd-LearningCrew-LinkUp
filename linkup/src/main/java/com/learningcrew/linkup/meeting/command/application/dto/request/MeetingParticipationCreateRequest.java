package com.learningcrew.linkup.meeting.command.application.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MeetingParticipationCreateRequest {
    private int memberId;
    private int meetingId;
    private int leaderId;
    private LocalDateTime participatedAt;
}

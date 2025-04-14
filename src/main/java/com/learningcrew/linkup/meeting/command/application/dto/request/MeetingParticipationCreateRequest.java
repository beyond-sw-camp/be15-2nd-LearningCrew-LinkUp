package com.learningcrew.linkup.meeting.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MeetingParticipationCreateRequest {
    private int memberId;
    private int meetingId;
    private int statusId = 1;
    private LocalDateTime participatedAt = LocalDateTime.now();
}

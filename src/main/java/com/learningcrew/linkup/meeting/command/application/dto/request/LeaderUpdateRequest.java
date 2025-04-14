package com.learningcrew.linkup.meeting.command.application.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LeaderUpdateRequest {
    @Min(value = 1)
    private final int leaderId;
    @Min(1)
    private final int meetingId;
}

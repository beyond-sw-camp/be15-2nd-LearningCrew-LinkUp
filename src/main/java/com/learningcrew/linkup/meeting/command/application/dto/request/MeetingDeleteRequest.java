package com.learningcrew.linkup.meeting.command.application.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class MeetingDeleteRequest {
    @Min(value = 1)
    private int meetingId;
}

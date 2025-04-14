package com.learningcrew.linkup.meeting.command.application.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ParticipantReviewCommandResponse {
    private long reviewId;
    private int revieweeId;
    private int meetingId;
    private int score;
    private LocalDateTime createdAt;
}

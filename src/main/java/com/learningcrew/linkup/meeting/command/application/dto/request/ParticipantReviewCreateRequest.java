package com.learningcrew.linkup.meeting.command.application.dto.request;

import lombok.Getter;

@Getter
public class ParticipantReviewCreateRequest {
    private int reviewerId;
    private int score;
}

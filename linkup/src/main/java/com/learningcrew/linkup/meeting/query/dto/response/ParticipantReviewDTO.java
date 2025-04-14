package com.learningcrew.linkup.meeting.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ParticipantReviewDTO {
    private Long reviewId;
    private String reviewerNickname;
    private String revieweeNickname;
    private String meetingTitle;
    private LocalDate meetingDate;
    private int score;
    private LocalDateTime createdAt;
}

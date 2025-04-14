package com.learningcrew.linkup.meeting.command.domain.repository;

import com.learningcrew.linkup.meeting.command.domain.aggregate.ParticipantReview;

public interface ParticipantReviewRepository {
    ParticipantReview save(ParticipantReview review);
}

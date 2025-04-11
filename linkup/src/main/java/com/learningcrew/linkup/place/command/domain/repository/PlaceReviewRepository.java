package com.learningcrew.linkup.place.command.domain.repository;

import com.learningcrew.linkup.place.command.domain.aggregate.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Integer> {
}

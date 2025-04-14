package com.learningcrew.linkup.place.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "place_review")
@NoArgsConstructor()
@Getter
@Setter
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "place_id")
    private int placeId;

    @Column(name= "participation_id")
    private int participationId;

    private String reviewContent;
    private String reviewImageUrl;
    private int ReviewScore;
    private char isVisible;
}

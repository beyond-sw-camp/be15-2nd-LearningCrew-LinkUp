package com.learningcrew.linkup.place.query.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class PlaceReviewResponse {
    private String reviewContent;
    private String reviewImageUrl;
    private int reviewScore;
}

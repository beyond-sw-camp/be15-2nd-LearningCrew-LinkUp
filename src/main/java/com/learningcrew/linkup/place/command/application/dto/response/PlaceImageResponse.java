package com.learningcrew.linkup.place.command.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceImageResponse {
    private Integer placeId;
    private String message;
}

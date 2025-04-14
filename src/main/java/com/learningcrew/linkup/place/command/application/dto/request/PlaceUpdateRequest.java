package com.learningcrew.linkup.place.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaceUpdateRequest {

    @NotNull
    private final int sportId;
    @NotBlank
    private final String placeName;
    @NotBlank
    private final String address;

    private final String description;
    private final String equipment;
    private final char isActive;
    private final int rentalCost;
}

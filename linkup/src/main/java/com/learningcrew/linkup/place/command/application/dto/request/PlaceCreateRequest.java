package com.learningcrew.linkup.place.command.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PlaceCreateRequest {
    @NotNull
    private final int ownerId;
    @NotNull
    private final int sportId;
    @NotBlank
    private final String placeName;
    @NotBlank
    private final String address;

    private final String description;
    private final String equipment;
    private char isActive = 'Y';
    @Min(value = 0)
    private final int rentalCost;

    private List<OperationTimeRequest> operationTimes;
}

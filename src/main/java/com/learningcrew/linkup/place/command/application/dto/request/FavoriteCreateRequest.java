package com.learningcrew.linkup.place.command.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteCreateRequest {
    @NotNull
    private int memberId;
}

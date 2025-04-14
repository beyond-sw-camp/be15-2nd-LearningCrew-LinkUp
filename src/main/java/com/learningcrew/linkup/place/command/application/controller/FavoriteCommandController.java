package com.learningcrew.linkup.place.command.application.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.place.command.application.dto.request.FavoriteCreateRequest;
import com.learningcrew.linkup.place.command.application.dto.response.FavoriteCommandResponse;
import com.learningcrew.linkup.place.command.application.service.FavoriteCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FavoriteCommandController {

    private final FavoriteCommandService favoriteCommandService;

    @PostMapping("/places/{placeId}")
    public ResponseEntity<ApiResponse<FavoriteCommandResponse>> createFavorite(
            @PathVariable("placeId") int placeId,
            @RequestBody @Validated FavoriteCreateRequest favoriteCreateRequest) {

        FavoriteCommandResponse response = favoriteCommandService.createFavorite(placeId, favoriteCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }
}

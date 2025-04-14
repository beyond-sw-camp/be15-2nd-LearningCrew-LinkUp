package com.learningcrew.linkup.place.command.application.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.place.command.application.dto.request.PlaceCreateRequest;
import com.learningcrew.linkup.place.command.application.dto.request.PlaceUpdateRequest;
import com.learningcrew.linkup.place.command.application.dto.response.PlaceCommandResponse;
import com.learningcrew.linkup.place.command.application.service.PlaceCommandService;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceCommandController {

    private final PlaceCommandService placeCommandService;
    @PostMapping("/place")
    public ResponseEntity<ApiResponse<PlaceCommandResponse>> createPlace(
            @RequestPart @Validated PlaceCreateRequest placeCreateRequest,
            @RequestPart(required = false) List<MultipartFile> placeImgs) {
        int placeId = placeCommandService.createPlace(placeCreateRequest, placeImgs);
        PlaceCommandResponse response = PlaceCommandResponse.builder()
                .placeId(placeId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }
    @PutMapping("/place/{placeId}")
    public ResponseEntity<ApiResponse<Void>> updatePlace(
            @PathVariable int placeId,
            @RequestPart PlaceUpdateRequest placeUpdateRequest
    ){
        placeCommandService.updatePlace(placeId,placeUpdateRequest);
        return ResponseEntity
                .ok(ApiResponse.success(null));
    }
}

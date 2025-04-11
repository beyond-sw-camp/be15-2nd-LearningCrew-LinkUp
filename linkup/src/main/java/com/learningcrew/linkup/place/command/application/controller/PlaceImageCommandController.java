package com.learningcrew.linkup.place.command.application.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.place.command.application.dto.request.OperationTimeRequest;
import com.learningcrew.linkup.place.command.application.dto.response.OperationTimeUpdateResponse;
import com.learningcrew.linkup.place.command.application.dto.response.PlaceImageResponse;
import com.learningcrew.linkup.place.command.application.service.PlaceImageCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceImageCommandController {

    private final PlaceImageCommandService placeImageCommandService;

    @PutMapping("/place/{placeId}/images")
    public ResponseEntity<ApiResponse<PlaceImageResponse>> updatePlaceImages(
            @PathVariable int placeId,
            @RequestPart(name = "placeImgs", required = false) List<MultipartFile> placeImgs) {

        PlaceImageResponse response = placeImageCommandService.updatePlaceImages(placeId, placeImgs);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}


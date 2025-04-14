package com.learningcrew.linkup.place.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.place.query.dto.request.PlaceListRequest;
import com.learningcrew.linkup.place.query.dto.response.PlaceDetailResponse;
import com.learningcrew.linkup.place.query.dto.response.PlaceListResponse;
import com.learningcrew.linkup.place.query.service.PlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceQueryController {
    private final PlaceQueryService placeQueryService;

    @GetMapping("/places")
    public ResponseEntity<ApiResponse<PlaceListResponse>> getPlaces(PlaceListRequest placeListRequest) {
        PlaceListResponse response = placeQueryService.getPlaces(placeListRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/place/{placeId}")
    public ResponseEntity<ApiResponse<PlaceDetailResponse>> getPlaceDetails(@PathVariable("placeId") int placeId) {
        PlaceDetailResponse detailResponse = placeQueryService.getPlaceDetails(placeId);
        return ResponseEntity.ok(ApiResponse.success(detailResponse));
    }
    @GetMapping("/admin/places")
    public ResponseEntity<ApiResponse<PlaceListResponse>> getPlacesByAdmin(PlaceListRequest placeListRequest) {
        PlaceListResponse response = placeQueryService.getPlaces(placeListRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}

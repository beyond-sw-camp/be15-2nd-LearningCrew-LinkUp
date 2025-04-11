package com.learningcrew.linkup.place.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.place.query.dto.request.FavoriteRequest;
import com.learningcrew.linkup.place.query.dto.response.FavoriteListResponse;
import com.learningcrew.linkup.place.query.service.FavoriteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FavoriteQueryController {

    private final FavoriteQueryService favoriteQueryService;

    @GetMapping("/user/{memberId}/favorite")
    public ResponseEntity<ApiResponse<FavoriteListResponse>> getFavorite(
            @PathVariable("memberId") int memberId,
            FavoriteRequest favoriteRequest) {
        FavoriteListResponse response = favoriteQueryService.getFavoriteList(memberId, favoriteRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
package com.learningcrew.linkup.place.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.place.query.dto.request.ReserveListRequest;
import com.learningcrew.linkup.place.query.dto.response.ReserveListResponse;
import com.learningcrew.linkup.place.query.service.ReservationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReservationQueryController {

    private final ReservationQueryService reservationQueryService;

    // 예약 성공 조회
    @GetMapping("/owner/{ownerId}/reserve")
    public ResponseEntity<ApiResponse<ReserveListResponse>> getReserve(
            @PathVariable("ownerId") int ownerId, ReserveListRequest reserveRequest) {
        ReserveListResponse response = reservationQueryService.getReserveList(ownerId,reserveRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}

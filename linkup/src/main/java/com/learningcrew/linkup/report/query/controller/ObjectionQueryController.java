package com.learningcrew.linkup.report.query.controller;

import com.learningcrew.linkup.report.query.dto.request.ObjectionSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.ObjectionListResponse;
import com.learningcrew.linkup.report.query.service.ObjectionQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/objections")
@RequiredArgsConstructor
@Tag(name = "이의 제기 조회", description = "이의 제기 내역 조회 API")
public class ObjectionQueryController {

    private final ObjectionQueryService objectionQueryService;

    @GetMapping
    @Operation(summary = "이의 제기 전체 조회", description = "관리자가 사용자 혹은 사업자가 제기한 이의 내역을 전체 조회한다.")
    public ResponseEntity<ObjectionListResponse> getObjections(ObjectionSearchRequest request) {
        return ResponseEntity.ok(objectionQueryService.getObjections(request));
    }

    @GetMapping("/status/{statusId}")
    @Operation(summary = "상태별 이의 제기 조회", description = "관리자가 사용자 혹은 사업자가 제기한 이의 내역을 상태별로 조회한다.")
    public ResponseEntity<ObjectionListResponse> getObjectionsByStatus(@PathVariable Integer statusId,
                                                                        @Parameter(hidden = true) ObjectionSearchRequest request) {
        request.setStatusId(statusId);
        return ResponseEntity.ok(objectionQueryService.getObjections(request));
    }

    @GetMapping("/user/{memberId}")
    @Operation(summary = "사용자별 이의 제기 조회", description = "관리자 혹은 특정 사용자가 해당 사용자가 제기한 이의 내역을 조회한다.")
    public ResponseEntity<ObjectionListResponse> getObjectionsByUser(@PathVariable Long memberId,
                                                                      @Parameter(hidden = true) ObjectionSearchRequest request) {
        request.setMemberId(memberId);
        return ResponseEntity.ok(objectionQueryService.getObjections(request));
    }
}

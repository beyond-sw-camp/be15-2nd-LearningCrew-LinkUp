package com.learningcrew.linkup.report.query.controller;

import com.learningcrew.linkup.report.query.dto.request.PenaltySearchRequest;
import com.learningcrew.linkup.report.query.dto.response.PenaltyListResponse;
import com.learningcrew.linkup.report.query.service.PenaltyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/penalty")
@RequiredArgsConstructor
@Tag(name = "사용자 제재 조회", description = "관리자 전용 사용자 제재 내역 조회 API")
public class PenaltyQueryController {

    private final PenaltyQueryService penaltyQueryService;

    @GetMapping
    @Operation(summary = "전체 제재 내역 조회", description = "관리자가 모든 제재 이력을 페이징하여 조회합니다.")
    public ResponseEntity<PenaltyListResponse> getPenalties(PenaltySearchRequest request) {
        return ResponseEntity.ok(penaltyQueryService.getPenalties(request));
    }

    @GetMapping("/{penaltyType}")
    @Operation(summary = "유형별 제재 내역 조회", description = "관리자가 제재 유형(post, comment, review)별로 제재 이력을 조회합니다.")
    public ResponseEntity<PenaltyListResponse> getPenaltiesByType(@PathVariable String penaltyType,
                                                                   @Parameter(hidden = true) PenaltySearchRequest request) {
        request.setPenaltyType(penaltyType);
        return ResponseEntity.ok(penaltyQueryService.getPenalties(request));
    }

    @GetMapping("/user/{memberId}")
    @Operation(summary = "사용자별 제재 내역 조회", description = "관리자 또는 사용자가 특정 사용자(memberId)의 모든 제재 이력을 조회합니다.")
    public ResponseEntity<PenaltyListResponse> getPenaltiesByUser(@PathVariable Long memberId,
                                                                   @Parameter(hidden = true) PenaltySearchRequest request) {
        request.setMemberId(memberId);
        return ResponseEntity.ok(penaltyQueryService.getPenalties(request));
    }

    @GetMapping("/user/{memberId}/{penaltyType}")
    @Operation(summary = "사용자별 + 유형별 제재 내역 조회", description = "특정 사용자의 특정 유형(post, comment, review)의 제재 이력을 조회합니다.")
    public ResponseEntity<PenaltyListResponse> getPenaltiesByUserAndType(@PathVariable Long memberId,
                                                                         @PathVariable String penaltyType,
                                                                         @Parameter(hidden = true) PenaltySearchRequest request) {
        return ResponseEntity.ok(penaltyQueryService.getPenaltiesByMemberAndType(memberId, penaltyType, request));
    }


}

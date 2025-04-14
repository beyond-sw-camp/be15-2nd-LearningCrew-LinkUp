package com.learningcrew.linkup.report.query.controller;

import com.learningcrew.linkup.report.query.dto.request.BlacklistSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.BlacklistListResponse;
import com.learningcrew.linkup.report.query.service.BlacklistQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blacklist")
@RequiredArgsConstructor
@Tag(name = "블랙리스트 조회", description = "관리자 전용 블랙리스트 사용자 목록 조회 API")
public class BlacklistQueryController {

    private final BlacklistQueryService blacklistQueryService;

    @GetMapping
    @Operation(summary = "블랙리스트 전체 조회", description = "서비스 이용이 제한된 사용자의 블랙리스트 목록을 조회한다.")
    public ResponseEntity<BlacklistListResponse> getBlacklist(BlacklistSearchRequest request) {
        return ResponseEntity.ok(blacklistQueryService.getBlacklist(request));
    }
}

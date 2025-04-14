package com.learningcrew.linkup.report.command.application.controller;

import com.learningcrew.linkup.report.command.application.dto.request.ReportRegisterRequest;
import com.learningcrew.linkup.report.command.application.dto.request.ReportStatusUpdateRequest;
import com.learningcrew.linkup.report.command.application.dto.response.ReportRegisterResponse;
import com.learningcrew.linkup.report.command.application.service.ReportCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
@Tag(name = "신고 등록", description = "사용자 신고 등록 API")
public class ReportCommandController {

    private final ReportCommandService reportCommandService;

    // 🔹 사용자 신고 (타겟이 유저)
    @PostMapping("/user")
    @Operation(summary = "사용자 신고", description = "사용자가 다른 사용자를 신고합니다.")
    public ResponseEntity<ReportRegisterResponse> reportUser(
            @RequestBody @Valid ReportRegisterRequest request
    ) {
        return ResponseEntity.ok(reportCommandService.registerReport(request));
    }

    // 🔹 게시글 신고
    @PostMapping("/post")
    @Operation(summary = "게시글 신고", description = "사용자가 게시글을 신고합니다.")
    public ResponseEntity<ReportRegisterResponse> reportPost(
            @RequestBody @Valid ReportRegisterRequest request
    ) {
        return ResponseEntity.ok(reportCommandService.registerReport(request));
    }

    // 🔹 댓글 신고
    @PostMapping("/comment")
    @Operation(summary = "댓글 신고", description = "사용자가 댓글을 신고합니다.")
    public ResponseEntity<ReportRegisterResponse> reportComment(
            @RequestBody @Valid ReportRegisterRequest request
    ) {
        return ResponseEntity.ok(reportCommandService.registerReport(request));
    }

    @PutMapping("/{reportId}")
    @Operation(summary = "신고 상태 변경", description = "관리자가 허위신고로 거절하거나, 처리 완료로 변경")
    public ResponseEntity<Void> updateReportStatus(
            @PathVariable Long reportId,
            @RequestBody ReportStatusUpdateRequest request
    ) {
        reportCommandService.updateReportStatus(reportId, request);
        return ResponseEntity.ok().build();
    }
}


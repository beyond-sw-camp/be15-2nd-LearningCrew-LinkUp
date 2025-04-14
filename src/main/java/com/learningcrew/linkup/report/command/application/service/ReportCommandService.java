package com.learningcrew.linkup.report.command.application.service;

import com.learningcrew.linkup.report.command.application.dto.request.ReportRegisterRequest;
import com.learningcrew.linkup.report.command.application.dto.request.ReportStatusUpdateRequest;
import com.learningcrew.linkup.report.command.application.dto.response.ReportRegisterResponse;

public interface ReportCommandService {

    /**
     * 사용자 신고 등록 처리
     * - 중복 신고 방지 포함
     * - 신고 이력 저장 후 응답 메시지 반환
     */
    ReportRegisterResponse registerReport(ReportRegisterRequest request);

    void updateReportStatus(Long reportId, ReportStatusUpdateRequest request);
}
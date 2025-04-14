package com.learningcrew.linkup.report.command.application.service;

import com.learningcrew.linkup.report.command.application.dto.request.HandleReportRequest;
import com.learningcrew.linkup.report.command.application.dto.response.ReportHandleResponse;

public interface ReportAdminService {
    ReportHandleResponse markAsFalseReport(Long reportId);
    ReportHandleResponse completeReportAndPenalize(Long reportId, HandleReportRequest request);
}

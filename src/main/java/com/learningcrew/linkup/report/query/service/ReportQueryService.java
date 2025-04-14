package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.report.query.dto.request.ReportSearchRequest;
import com.learningcrew.linkup.report.query.dto.request.ReporterSearchRequest;
import com.learningcrew.linkup.report.query.dto.request.ReporteeSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.*;

public interface ReportQueryService {

    ReportListResponse getReports(ReportSearchRequest request);

    ReportUserListResponse<ReportUserCountDTO> getReporterListByCount(ReporterSearchRequest request);

    ReportUserListResponse<ReportUserScoreDTO> getReporteeListByScore(ReporteeSearchRequest request);
}

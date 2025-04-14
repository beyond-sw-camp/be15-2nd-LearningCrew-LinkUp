package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.report.query.dto.request.ReportSearchRequest;
import com.learningcrew.linkup.report.query.dto.request.ReporterSearchRequest;
import com.learningcrew.linkup.report.query.dto.request.ReporteeSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.*;
import com.learningcrew.linkup.report.query.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportMapper reportMapper;

    // 전체 및 조건별 신고 내역 조회
    @Override
    public ReportListResponse getReports(ReportSearchRequest request) {
        List<ReportDTO> reports = reportMapper.selectReports(request);
        long totalItems = reportMapper.countReports(request);

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalItems(totalItems)
                .totalPage((int) Math.ceil((double) totalItems / request.getSize()))
                .build();

        return ReportListResponse.builder()
                .reports(reports)
                .pagination(pagination)
                .build();
    }

    // 누적 신고 횟수별 신고자 목록 조회 (페이징)
    @Override
    public ReportUserListResponse<ReportUserCountDTO> getReporterListByCount(ReporterSearchRequest request) {
        List<ReportUserCountDTO> users = reportMapper.selectReporterListByCount(request);
        long totalItems = reportMapper.countReporterListByCount();

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalItems(totalItems)
                .totalPage((int) Math.ceil((double) totalItems / request.getSize()))
                .build();

        return ReportUserListResponse.<ReportUserCountDTO>builder()
                .users(users)
                .pagination(pagination)
                .build();
    }

    // 누적 신고 점수별 피신고자 목록 조회 (페이징)
    @Override
    public ReportUserListResponse<ReportUserScoreDTO> getReporteeListByScore(ReporteeSearchRequest request) {
        List<ReportUserScoreDTO> users = reportMapper.selectReporteeListByScore(request);
        long totalItems = reportMapper.countReporteeListByScore();

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalItems(totalItems)
                .totalPage((int) Math.ceil((double) totalItems / request.getSize()))
                .build();

        return ReportUserListResponse.<ReportUserScoreDTO>builder()
                .users(users)
                .pagination(pagination)
                .build();
    }
}

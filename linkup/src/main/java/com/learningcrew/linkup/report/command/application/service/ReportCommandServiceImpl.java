package com.learningcrew.linkup.report.command.application.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.report.command.application.dto.request.ReportRegisterRequest;
import com.learningcrew.linkup.report.command.application.dto.request.ReportStatusUpdateRequest;
import com.learningcrew.linkup.report.command.application.dto.response.ReportRegisterResponse;
import com.learningcrew.linkup.report.command.application.mapper.ReportRegisterMapper;
import com.learningcrew.linkup.report.command.domain.aggregate.ReportHistory;
import com.learningcrew.linkup.report.command.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ReportCommandServiceImpl implements ReportCommandService {

    private final ReportRepository reportRepository;
    private final ReportRegisterMapper reportRegisterMapper;

    @Override
    public ReportRegisterResponse registerReport(ReportRegisterRequest request) {
        // 중복 신고 여부 확인
        boolean isDuplicate = reportRepository.findByReporterIdAndTargetIdAndReportTypeIdAndPostIdAndCommentId(
                request.getReporterMemberId(),
                request.getTargetMemberId(),
                request.getReportTypeId(),
                request.getPostId(),
                request.getCommentId()
        ).isPresent();

        if (isDuplicate) {
            throw new BusinessException(ErrorCode.REPORT_ALREADY_EXISTS);
        }

        // 신고 엔티티 생성
        ReportHistory report = ReportHistory.builder()
                .reporterId(request.getReporterMemberId())
                .targetId(request.getTargetMemberId())
                .postId(request.getPostId())
                .commentId(request.getCommentId())
                .reportTypeId(request.getReportTypeId())
                .statusId(1) // 처리중 상태
                .reason(request.getReason())
                .build();

        // 저장 및 응답 생성
        ReportHistory saved = reportRepository.save(report);
        return reportRegisterMapper.toResponse(saved);
    }

    @Override
    public void updateReportStatus(Long reportId, ReportStatusUpdateRequest request) {
        ReportHistory report = reportRepository.findById(reportId)
                .orElseThrow(() -> new BusinessException(ErrorCode.REPORT_NOT_FOUND));

        report.setStatusId(request.getStatusId()); // ex) 2 = 완료, 3 = 거절
    }
}

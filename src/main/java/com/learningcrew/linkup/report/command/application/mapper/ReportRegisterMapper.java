package com.learningcrew.linkup.report.command.application.mapper;

import com.learningcrew.linkup.report.command.application.dto.response.ReportRegisterResponse;
import com.learningcrew.linkup.report.command.domain.aggregate.ReportHistory;
import org.springframework.stereotype.Component;

@Component
public class ReportRegisterMapper {

    public ReportRegisterResponse toResponse(ReportHistory entity) {
        return ReportRegisterResponse.builder()
                .reportId(entity.getId())
                .reporterMemberId(entity.getReporterId())
                .targetMemberId(entity.getTargetId())
                .reportTypeId(entity.getReportTypeId())
                .message("신고가 정상적으로 등록되었습니다.")
                .build();
    }
}

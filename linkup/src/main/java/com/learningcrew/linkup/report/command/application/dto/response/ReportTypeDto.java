package com.learningcrew.linkup.report.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportTypeDto {

    private Integer reportTypeId;  // 신고 유형 ID
    private String reportType;     // 신고 유형 (예: `post`, `comment`)
}

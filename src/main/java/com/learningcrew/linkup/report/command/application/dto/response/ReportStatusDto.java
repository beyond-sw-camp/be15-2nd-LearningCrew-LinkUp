package com.learningcrew.linkup.report.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportStatusDto {

    private Integer statusId;      // 상태 ID
    private String description;    // 상태 설명 (예: "처리중", "완료")
}

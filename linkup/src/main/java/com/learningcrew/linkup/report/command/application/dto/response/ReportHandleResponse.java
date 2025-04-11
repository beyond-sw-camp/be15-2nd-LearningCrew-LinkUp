package com.learningcrew.linkup.report.command.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportHandleResponse {
    private Long reportId;
    private Integer statusId;
    private String message;
}

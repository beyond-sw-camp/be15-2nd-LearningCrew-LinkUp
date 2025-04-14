package com.learningcrew.linkup.report.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportStatusUpdateRequest {
    private Integer statusId; // 처리 상태 (2: 처리 완료, 3: 거절 등)
    private String reason;   // 관리자 처리 사유 (선택)
}
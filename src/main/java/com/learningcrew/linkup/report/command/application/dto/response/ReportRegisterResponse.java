package com.learningcrew.linkup.report.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 신고 등록 응답 DTO
 */
@Getter
@Builder
@AllArgsConstructor
public class ReportRegisterResponse {

    private Long reportId;           // 생성된 신고 ID
    private Integer reporterMemberId;   // 신고자 ID
    private Integer targetMemberId;     // 신고 대상자 ID
    private Byte reportTypeId;          // 신고 유형 ID
    private String message;             // 처리 메시지 (ex: 등록 성공)
}

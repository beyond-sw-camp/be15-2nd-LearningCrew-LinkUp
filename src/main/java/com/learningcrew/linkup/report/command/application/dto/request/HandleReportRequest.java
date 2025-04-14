package com.learningcrew.linkup.report.command.application.dto.request;

import com.learningcrew.linkup.report.command.domain.aggregate.PenaltyType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HandleReportRequest {

    private Long penaltyTargetId;       // 제재 대상 ID (게시글/댓글/리뷰 중 하나)
    private PenaltyType penaltyType;    // POST, COMMENT, REVIEW 중 하나
    private String reason;              // 제재 사유
    private LocalDateTime expiredAt;    // 만료 일시 (null 가능)
}

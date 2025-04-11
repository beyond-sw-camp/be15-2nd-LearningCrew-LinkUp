package com.learningcrew.linkup.report.command.application.dto.request;

import com.learningcrew.linkup.report.command.domain.aggregate.PenaltyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyRegisterRequest {
    private Integer memberId; // 제재 대상
    private PenaltyType penaltyType;
    private Integer penaltyTargetId; // postId, commentId, reviewId 중 하나
    private String reason;
    private LocalDateTime expiredAt; // 제재 만료일
}
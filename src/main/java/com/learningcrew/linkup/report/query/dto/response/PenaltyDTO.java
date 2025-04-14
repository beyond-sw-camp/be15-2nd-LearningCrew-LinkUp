package com.learningcrew.linkup.report.query.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyDTO {

    private Long penaltyId;

    private Long memberId;
    private String memberName;

    private String penaltyType;

    private String reason;

    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    private String isActive; // "Y" or "N"

    private Long postId;
    private Long commentId;
    private Long reviewId;
}

package com.learningcrew.linkup.report.query.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectionDTO {

    private Long objectionId;

    private Long penaltyId;

    private Long memberId;
    private String userName;

    private String status;

    private String reason;

    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
}

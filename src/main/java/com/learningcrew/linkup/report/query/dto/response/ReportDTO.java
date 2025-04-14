package com.learningcrew.linkup.report.query.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private Long reportId;

    private Long reporterMemberId;
    private String reporterName;

    private Long targetMemberId;
    private String targetName;

    private Byte reportTypeId;
    private String reportType;

    private String status;

    private String reason;

    private Long postId;
    private Long commentId;

    private LocalDateTime createdAt;
}

package com.learningcrew.linkup.report.command.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 신고 등록 요청 DTO
 */
@Getter
@Setter
public class ReportRegisterRequest {

    @NotNull(message = "신고자 ID는 필수입니다.")
    private Integer reporterMemberId;

    @NotNull(message = "신고 대상자 ID는 필수입니다.")
    private Integer targetMemberId;

    private Integer postId;     // 게시글 신고 시
    private Long commentId;     // 댓글 신고 시

    @NotNull(message = "신고 유형 ID는 필수입니다.")
    private Byte reportTypeId;

    @NotNull(message = "신고 사유는 필수입니다.")
    @Size(min = 1, max = 1000, message = "신고 사유는 1자 이상 1000자 이하여야 합니다.")
    private String reason;
}

package com.learningcrew.linkup.report.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenaltySearchRequest {

    private int page = 1;
    private int size = 10;

    private String penaltyType; // post, comment, review
    private Long memberId;      // 특정 사용자 조회 시 사용

    public int getOffset() {
        return (page - 1) * size;
    }
}

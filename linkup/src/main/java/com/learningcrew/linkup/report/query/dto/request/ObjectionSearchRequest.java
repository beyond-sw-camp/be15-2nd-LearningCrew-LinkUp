package com.learningcrew.linkup.report.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectionSearchRequest {

    private int page = 1;
    private int size = 10;

    private Integer statusId;   // 상태별 필터링
    private Long memberId;      // 사용자별 필터링

    public int getOffset() {
        return (page - 1) * size;
    }
}

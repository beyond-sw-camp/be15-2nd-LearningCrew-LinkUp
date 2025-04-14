package com.learningcrew.linkup.report.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportSearchRequest {

    private int page = 1;
    private int size = 10;

    private Long reporterMemberId;
    private Long targetMemberId;
    private Byte reportTypeId;
    private Integer statusId;

    public int getOffset() {
        return (page - 1) * size;
    }
}

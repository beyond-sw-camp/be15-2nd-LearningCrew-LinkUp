package com.learningcrew.linkup.report.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlacklistSearchRequest {

    private int page = 1;
    private int size = 10;

    public int getOffset() {
        return (page - 1) * size;
    }
}

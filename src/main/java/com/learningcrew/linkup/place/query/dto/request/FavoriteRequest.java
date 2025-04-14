package com.learningcrew.linkup.place.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteRequest {
    private Integer page = 1;
    private Integer size = 5;
    private int memberId; // 컨트롤러에서 path variable로 받은 값을 세팅

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }
}

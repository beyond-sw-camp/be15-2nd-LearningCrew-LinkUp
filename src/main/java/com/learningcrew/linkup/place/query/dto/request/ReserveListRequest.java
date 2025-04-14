package com.learningcrew.linkup.place.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveListRequest {
    private Integer page = 1;
    private Integer size = 10;
    private Integer ownerId;
    private Integer reservationId;
    private Integer statusId;

    public int getOffset(){
        return (page - 1) * size;
    }

    public int getLimit(){
        return size;
    }
}

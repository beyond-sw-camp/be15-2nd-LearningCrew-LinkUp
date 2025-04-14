package com.learningcrew.linkup.place.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteDto {
    private Integer placeId;
    private String placeName;
    private String address;
}

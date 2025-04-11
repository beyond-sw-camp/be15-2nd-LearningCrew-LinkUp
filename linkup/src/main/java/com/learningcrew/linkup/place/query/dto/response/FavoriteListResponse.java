package com.learningcrew.linkup.place.query.dto.response;

import com.learningcrew.linkup.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FavoriteListResponse {
    private List<FavoriteDto> favorites;
    private Pagination pagination;
}

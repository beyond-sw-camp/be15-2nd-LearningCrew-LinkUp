package com.learningcrew.linkup.community.query.dto.response;

import com.learningcrew.linkup.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostListResponse {
    private List<PostDTO> posts;
    private Pagination pagination;
}

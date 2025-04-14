package com.learningcrew.linkup.community.query.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PostDetailResponse {
    private int postId;
    private String title;
    private String content;
    private boolean postIsNotice;

    private List<String> imageUrl;
    private List<PostCommentResponse> comments;
}

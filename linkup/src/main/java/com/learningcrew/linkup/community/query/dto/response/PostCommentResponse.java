package com.learningcrew.linkup.community.query.dto.response;

import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class PostCommentResponse {

    private String content;
    private List<PostComment> comments;
    private int totalCount;
}

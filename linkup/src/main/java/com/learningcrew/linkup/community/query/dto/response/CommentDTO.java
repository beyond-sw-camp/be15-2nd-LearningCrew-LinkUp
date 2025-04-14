package com.learningcrew.linkup.community.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {
    private Integer commentId;
    private Integer postId;
    private Integer userId;
    private String content;
    private int likeCount;
    private LocalDateTime createdAt;
}

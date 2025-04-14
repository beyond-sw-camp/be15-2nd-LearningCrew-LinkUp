package com.learningcrew.linkup.community.query.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDetailResponse {
    private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private Boolean isNotice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> imageUrls;
    private List<CommentDTO> comments;
}

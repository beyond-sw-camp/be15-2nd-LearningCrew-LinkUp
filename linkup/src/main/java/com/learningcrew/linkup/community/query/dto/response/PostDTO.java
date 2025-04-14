package com.learningcrew.linkup.community.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private Boolean isNotice;
    private String createdAt;
    private String updatedAt;


}
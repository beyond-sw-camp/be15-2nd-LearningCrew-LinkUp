package com.learningcrew.linkup.community.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostCommentCreateRequestDTO {

    private Integer userId;

    private Integer postId;

    private String commentContent;


}

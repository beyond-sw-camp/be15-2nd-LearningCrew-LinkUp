package com.learningcrew.linkup.community.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostCommentCreateRequestDTO {

    @NotNull(message = "userId는 필수입니다.")
    private Integer userId;

    @NotNull(message = "postId는 필수입니다.")
    private Integer postId;

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String commentContent;


}

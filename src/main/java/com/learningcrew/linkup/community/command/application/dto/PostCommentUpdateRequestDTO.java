package com.learningcrew.linkup.community.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PostCommentUpdateRequestDTO {

    private BigInteger postCommentId;

    @NotBlank
    private String commentContent;

}

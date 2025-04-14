package com.learningcrew.linkup.community.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class PostCommentLikeRequestDTO {
    private BigInteger postCommentId;
    private Integer userId;
}

package com.learningcrew.linkup.community.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostLikeRequestDTO {
    @NotNull
    private Integer postId;  // 좋아요를 추가/취소할 게시글 ID

    @NotNull
    private Integer userId;  // 좋아요를 추가/취소하는 사용자 ID



}

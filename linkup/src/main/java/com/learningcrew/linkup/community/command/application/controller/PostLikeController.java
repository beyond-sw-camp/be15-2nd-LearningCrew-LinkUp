package com.learningcrew.linkup.community.command.application.controller;

import com.learningcrew.linkup.community.command.application.service.PostLikeService;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.aggregate.PostLike;
import com.learningcrew.linkup.community.command.domain.repository.PostLikeRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Like", description = "좋아요")
public class PostLikeController {

    private final PostLikeService postLikeService;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;

    @PostMapping("/posts/like")
    @Operation(summary = "게시글 좋아요", description = "게시글에 좋아요 등록 또는 취소한다.")
    public PostLike addLike(int postId, int userId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        PostLike postLike = PostLike.builder()
                .post(post)
                .user(user)
                .build();

        return postLikeRepository.save(postLike);
    }

    // 게시글 좋아요 취소
    public void removeLike(int postId, int userId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        PostLike postLike = postLikeRepository.findByPost_PostIdAndUser_UserId(postId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.LIKE_NOT_FOUND));

        postLikeRepository.delete(postLike);
    }
}
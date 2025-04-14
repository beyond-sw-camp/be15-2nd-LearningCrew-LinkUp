package com.learningcrew.linkup.community.command.application.controller;

import com.learningcrew.linkup.community.command.application.service.PostCommentLikeService;
import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import com.learningcrew.linkup.community.command.domain.aggregate.PostCommentLike;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentLikeRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentRepository;
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

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Like", description = "좋아요")
public class PostCommentLikeController {

    private final PostCommentLikeService postCommentLikeService;
    private PostCommentRepository postCommentRepository;
    private UserRepository userRepository;
    private PostCommentLikeRepository postCommentLikeRepository;

    // 게시글 댓글 좋아요
    @PostMapping("/{postId}/postComment/{postCommentId}/like")
    @Operation(summary = "게시글 댓글 좋아요", description = "게시글의 댓글에 좋아요 등록 또는 취소한다.")
    public PostCommentLike addLike(int userId, BigInteger postCommentId) {
        PostComment postComment = postCommentRepository.findById(postCommentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        PostCommentLike postCommentLike = PostCommentLike.builder()
                .postComment(postComment)
                .user(user)
                .build();

        return postCommentLikeRepository.save(postCommentLike);
    }

    // 댓글에 좋아요 취소
    public void removeLike(int userId, BigInteger postCommentId) {
        PostComment postComment = postCommentRepository.findById(postCommentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        PostCommentLike postCommentLike = postCommentLikeRepository.findByUser_UserIdAndPostComment_postCommentId(userId, postCommentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.LIKE_NOT_FOUND));

        postCommentLikeRepository.delete(postCommentLike);
    }
}
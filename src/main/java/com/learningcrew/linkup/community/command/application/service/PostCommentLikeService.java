package com.learningcrew.linkup.community.command.application.service;

import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import com.learningcrew.linkup.community.command.domain.aggregate.PostCommentLike;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentLikeRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentRepository;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class PostCommentLikeService {

    private final PostCommentLikeRepository postCommentLikeRepository;
    private final PostCommentRepository postCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostCommentLike likeComment(Integer userId, BigInteger postCommentId) {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 댓글 조회
        PostComment postComment = postCommentRepository.findById(postCommentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));

        // 좋아요 저장
        PostCommentLike like = PostCommentLike.builder()
                .user(user)
                .postComment(postComment)
                .build();

        return postCommentLikeRepository.save(like);
    }
}


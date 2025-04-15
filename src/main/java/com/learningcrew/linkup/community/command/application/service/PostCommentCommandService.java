package com.learningcrew.linkup.community.command.application.service;

import com.learningcrew.linkup.community.command.application.dto.PostCommentCreateRequestDTO;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import com.learningcrew.linkup.community.command.domain.constants.PostCommentIsDeleted;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
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
public class PostCommentCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCommentRepository postCommentRepository;

    /* 1. 댓글 등록 */
    @Transactional
    public PostComment createPostComment(PostCommentCreateRequestDTO postCommentCreateRequestDTO) {
        // 1. 게시글 조회
        Post post = postRepository.findById(postCommentCreateRequestDTO.getPostId())
                .filter(p -> !"Y".equals(p.getIsDeleted())) // 소프트 삭제된 게시글은 필터링
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        // 2. 사용자 조회
        User user = userRepository.findById(postCommentCreateRequestDTO.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 3. 댓글 생성
        PostComment postComment = PostComment.builder()
                .post(post)
                .userId(user.getUserId())
                .commentContent(postCommentCreateRequestDTO.getCommentContent())
                .build();

        // 4. 댓글 저장
        return postCommentRepository.save(postComment);
    }

    /* 2. 댓글 삭제 (Soft Delete) */
    @Transactional
    public void deletePostComment(BigInteger postCommentId) {
        PostComment postComment = postCommentRepository.findByPostCommentIdAndCommentIsDeleted(
                        postCommentId, "N")
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));


        // 2. 소프트 삭제 처리
        postComment.softDelete();

        // 3. 저장
        postCommentRepository.save(postComment);
    }
}
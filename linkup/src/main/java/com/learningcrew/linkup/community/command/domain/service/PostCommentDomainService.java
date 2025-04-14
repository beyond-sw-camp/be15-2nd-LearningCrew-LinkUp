package com.learningcrew.linkup.community.command.domain.service;

import com.learningcrew.linkup.community.command.application.dto.PostCommentCreateRequestDTO;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentDomainService {

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostComment createPostComment(PostCommentCreateRequestDTO postCommentCreateRequestDTO) {
        Post post = postRepository.findByPostId(postCommentCreateRequestDTO.getPostId())
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        User user = userRepository.findByUserId(postCommentCreateRequestDTO.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        String postCommentContent = postCommentCreateRequestDTO.getPostCommentContent();
        PostComment postComment = new PostComment(post, user, postCommentContent);

        return postCommentRepository.save(postComment);


    }

    }
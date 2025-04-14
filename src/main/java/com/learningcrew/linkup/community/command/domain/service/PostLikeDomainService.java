package com.learningcrew.linkup.community.command.domain.service;

import com.learningcrew.linkup.community.command.application.dto.PostLikeRequestDTO;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.aggregate.PostLike;
import com.learningcrew.linkup.community.command.domain.repository.PostLikeRepository;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeDomainService {
    @Autowired
    private PostLikeRepository postLikeRepository;  // 게시글 좋아요 레포지토리

    @Autowired
    private PostRepository postRepository;  // 게시글 레포지토리

    @Autowired
    private UserRepository userRepository;  // 사용자 레포지토리

    @Transactional
    public void addPostLike(int postId, int userId) {
        // 게시글과 사용자 조회
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 이미 좋아요가 존재하는지 확인
        PostLike existingPostLike = postLikeRepository.findByPost_PostIdAndUser_UserId(postId, userId)
                .orElse(null);

        if (existingPostLike == null) {
            // 좋아요가 없으면 추가
            PostLike newPostLike = new PostLike();
            newPostLike.setPost(post);
            newPostLike.setUser(user);
            postLikeRepository.save(newPostLike);
        }
    }

    @Transactional
    public void removePostLike(int postId, int userId) {
        // 게시글과 사용자 조회
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 게시글과 사용자의 좋아요 찾기
        PostLike postLike = postLikeRepository.findByPost_PostIdAndUser_UserId(postId, userId)
                .orElseThrow(() -> new RuntimeException("PostLike not found"));

        // 좋아요 삭제
        postLikeRepository.delete(postLike);


    }

    public void save(PostLikeRequestDTO postLikeRequestDTO) {
    }
}


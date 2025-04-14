package com.learningcrew.linkup.community.command.domain.service;

import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import com.learningcrew.linkup.community.command.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostDomainService {
    private final PostRepository postRepository;

    public PostDomainService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // postId 로 post 조회
    public Post findByPostId(int postId) {
        return postRepository.findByPostId(postId).orElse(null);
    }
}

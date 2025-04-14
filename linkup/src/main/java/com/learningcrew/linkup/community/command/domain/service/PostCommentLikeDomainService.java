package com.learningcrew.linkup.community.command.domain.service;

import com.learningcrew.linkup.community.command.domain.aggregate.PostCommentLike;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentLikeRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;


@Service
public class PostCommentLikeDomainService {

    private final PostCommentLikeRepository postCommentLikeRepository;

    public PostCommentLikeDomainService(PostCommentLikeRepository postCommentLikeRepository) {
        this.postCommentLikeRepository = postCommentLikeRepository;
    }

    // 해당 게시글 댓글에 대해 좋아요를 했는지 조회
    public Optional<PostCommentLike> findByUser_UserIdAndPostComment_postCommentId(int userId, BigInteger postCommentId) {
        return postCommentLikeRepository.findByUser_UserIdAndPostComment_postCommentId(userId, postCommentId);
    }

    // 좋아요 저장
    public void save(int commentLikeId) {
        postCommentLikeRepository.save(commentLikeId);
    }

    // 좋아요 삭제
    public void deleteById(int commentLikeId) {
        postCommentLikeRepository.deleteById(commentLikeId);
    }
}
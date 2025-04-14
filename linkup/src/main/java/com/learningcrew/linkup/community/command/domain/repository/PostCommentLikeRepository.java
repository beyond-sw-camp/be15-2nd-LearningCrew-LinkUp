package com.learningcrew.linkup.community.command.domain.repository;

import com.learningcrew.linkup.community.command.domain.aggregate.PostCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface PostCommentLikeRepository extends JpaRepository<PostCommentLike, Integer> {

    void deleteById(int postCommentLikeId);

    void save(int postCommentLikeId);

    Optional<PostCommentLike> findByUser_UserIdAndPostComment_postCommentId(int UserId, BigInteger postCommentId);
}
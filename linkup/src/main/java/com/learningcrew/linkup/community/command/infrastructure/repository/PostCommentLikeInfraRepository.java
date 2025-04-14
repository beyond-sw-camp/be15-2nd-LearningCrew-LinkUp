package com.learningcrew.linkup.community.command.infrastructure.repository;

import com.learningcrew.linkup.community.command.domain.aggregate.PostCommentLike;
import com.learningcrew.linkup.community.command.domain.repository.PostCommentLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface PostCommentLikeInfraRepository
        extends JpaRepository<PostCommentLike, Integer>, PostCommentLikeRepository {
    Optional<PostCommentLike> findByUser_UserIdAndPostComment_PostCommentId(int UserId, BigInteger postCommentId);
}

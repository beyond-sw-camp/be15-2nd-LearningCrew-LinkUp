package com.learningcrew.linkup.community.command.domain.repository;

import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, BigInteger> {
    Optional<PostComment> findByPostCommentIdAndPostCommentIsDeleted(BigInteger postCommentId, String postCommentIsDeleted);
//    Optional<PostComment> findByPostCommentId(BigInteger postCommentId);

}

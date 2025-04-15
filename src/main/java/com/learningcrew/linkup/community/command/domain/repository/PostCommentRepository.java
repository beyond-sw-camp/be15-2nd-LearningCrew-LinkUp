package com.learningcrew.linkup.community.command.domain.repository;

import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, BigInteger> {

    // 'postCommentId'로 PostComment를 조회하는 메서드
    Optional<PostComment> findByPostCommentIdAndCommentIsDeleted(BigInteger postCommentId, String commentIsDeleted);

    // 'Post' 객체의 'postId'로 'PostComment'를 조회하는 메서드
    List<PostComment> findByPost_PostIdAndCommentIsDeleted(BigInteger postId, String commentIsDeleted);


    // 예시: 댓글 삭제 상태가 'Y'인 댓글만 찾는 메서드
    List<PostComment> findByCommentIsDeleted(String commentIsDeleted);

    // 예시: 특정 게시글에 대한 댓글 찾기 (기존 코드와 동일하게 수정)
    List<PostComment> findByPost_PostId(int postId); // postId로 댓글 찾기

    // 예시: 특정 회원의 댓글 찾기
    List<PostComment> findByUserId(int userId);
}

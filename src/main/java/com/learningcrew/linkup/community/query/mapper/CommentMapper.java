package com.learningcrew.linkup.community.query.mapper;

import com.learningcrew.linkup.community.query.dto.request.CommunitySearchRequest;
import com.learningcrew.linkup.community.query.dto.response.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 전체 댓글 목록 (페이징 + 검색)
    List<CommentDTO> selectAllComments(CommunitySearchRequest request);

    long countAllComments(CommunitySearchRequest request);

    // 특정 회원 댓글 목록
    List<CommentDTO> selectCommentsByUser(@Param("userId") int userId,
                                           @Param("offset") int offset,
                                           @Param("limit") int limit);

    long countCommentsByUser(@Param("userId") int userId);

    // 특정 게시글 댓글 조회 (PostDetailResponse용)
    List<CommentDTO> selectCommentsByPostId(@Param("postId") int postId);
}

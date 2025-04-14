package com.learningcrew.linkup.community.query.mapper;


import com.learningcrew.linkup.community.query.dto.request.PostRequest;
import com.learningcrew.linkup.community.query.dto.response.PostDTO;
import com.learningcrew.linkup.community.query.dto.response.PostDetailResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    // 게시글 목록 조회 (페이징 + 검색 조건)
    List<PostDTO> selectAllPosts(PostRequest postRequest);

    // 게시글 개수 조회
    long countPosts(PostRequest postRequest);

    // 게시글 상세 조회
    PostDetailResponse selectPostDetail(@Param("postId") int postId);

    // 게시글 등록
    int insertPost(PostRequest PostRequest);

    // 게시글 수정
    int updatePost(PostRequest PostRequest);

    // 게시글 삭제 (soft delete)
    int deletePost(@Param("postId") int postId);
}



package com.learningcrew.linkup.community.query.mapper;

import com.learningcrew.linkup.community.query.dto.request.CommunitySearchRequest;
import com.learningcrew.linkup.community.query.dto.response.PostDTO;
import com.learningcrew.linkup.community.query.dto.response.PostDetailResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    // 게시글 전체 조회
    List<PostDTO> selectAllPosts(CommunitySearchRequest request);

    // 게시글 수 조회
    long countAllPosts(CommunitySearchRequest request);

    // 게시글 상세 조회
    PostDetailResponse selectPostDetail(int postId);

    // 게시글 이미지 URL 조회
    List<String> selectPostImageUrlsByPostId(int postId);

    // 특정 회원 게시글 조회
    List<PostDTO> selectPostsByUser(@Param("userId") int userId,
                                    @Param("offset") int offset,
                                    @Param("limit") int limit);
    // 특정 회원 게시글 수 조회
    long countPostsByUser(int userId);
}

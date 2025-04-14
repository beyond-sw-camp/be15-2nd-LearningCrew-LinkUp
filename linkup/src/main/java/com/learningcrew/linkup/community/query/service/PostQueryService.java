package com.learningcrew.linkup.community.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.community.query.dto.request.CommunitySearchRequest;
import com.learningcrew.linkup.community.query.dto.response.CommentDTO;
import com.learningcrew.linkup.community.query.dto.response.PostDTO;
import com.learningcrew.linkup.community.query.dto.response.PostDetailResponse;
import com.learningcrew.linkup.community.query.dto.response.PostListResponse;
import com.learningcrew.linkup.community.query.mapper.CommentMapper;
import com.learningcrew.linkup.community.query.mapper.PostMapper;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public PostListResponse getPosts(CommunitySearchRequest request) {
        List<PostDTO> posts = postMapper.selectAllPosts(request);
        long total = postMapper.countAllPosts(request);

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalPage((int) Math.ceil((double) total / request.getSize()))
                .totalItems(total)
                .build();

        return PostListResponse.builder()
                .posts(posts)
                .pagination(pagination)
                .build();
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPostDetail(int postId) {
        PostDetailResponse post = postMapper.selectPostDetail(postId);
        if (post == null) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND);
        }

        // 댓글 및 이미지 URL 추가
        List<CommentDTO> comments = commentMapper.selectCommentsByPostId(postId);
        List<String> imageUrls = postMapper.selectPostImageUrlsByPostId(postId); // 수정된 부분

        post.setComments(comments);
        post.setImageUrls(imageUrls);

        return post;
    }



    @Transactional(readOnly = true)
    public PostListResponse getPostsByUser(int userId, int page, int size) {
        int offset = (page - 1) * size;

        List<PostDTO> posts = postMapper.selectPostsByUser(userId, offset, size);
        long total = postMapper.countPostsByUser(userId);

        Pagination pagination = Pagination.builder()
                .currentPage(page)
                .totalPage((int) Math.ceil((double) total / size))
                .totalItems(total)
                .build();

        return PostListResponse.builder()
                .posts(posts)
                .pagination(pagination)
                .build();
    }
}

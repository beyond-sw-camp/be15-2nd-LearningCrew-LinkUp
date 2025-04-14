package com.learningcrew.linkup.community.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.community.query.dto.response.PostDTO;
import com.learningcrew.linkup.community.query.dto.request.PostRequest;
import com.learningcrew.linkup.community.query.dto.response.PostDetailResponse;
import com.learningcrew.linkup.community.query.dto.response.PostListResponse;
import com.learningcrew.linkup.community.query.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryService {

        private final PostMapper postMapper;

        @Transactional(readOnly = true)
        public PostListResponse getPosts(PostRequest postRequest) {

            List<PostDTO> posts = postMapper.selectAllPosts(postRequest);
            long totalItems = postMapper.countPosts(postRequest);
            int page = postRequest.getPage();
            int size = postRequest.getSize();
            int totalPage = (int) Math.ceil((double) totalItems / size);

            return PostListResponse.builder()
                    .posts(posts)
                    .pagination(Pagination.builder()
                            .currentPage(page)
                            .totalPage(totalPage)
                            .totalItems(totalItems)
                            .build())
                    .build();
        }

        @Transactional(readOnly = true)
        public PostDetailResponse getPostDetail(int postId) {
            return postMapper.selectPostDetail(postId);
        }
    }

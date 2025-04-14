package com.learningcrew.linkup.community.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.community.query.dto.request.CommunitySearchRequest;
import com.learningcrew.linkup.community.query.dto.response.CommentDTO;
import com.learningcrew.linkup.community.query.dto.response.CommentListResponse;
import com.learningcrew.linkup.community.query.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public CommentListResponse getAllComments(CommunitySearchRequest request) {
        List<CommentDTO> comments = commentMapper.selectAllComments(request);
        long total = commentMapper.countAllComments(request);

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalPage((int) Math.ceil((double) total / request.getSize()))
                .totalItems(total)
                .build();

        return CommentListResponse.builder()
                .comments(comments)
                .pagination(pagination)
                .build();
    }

    @Transactional(readOnly = true)
    public CommentListResponse getCommentsByUser(int userId, int page, int size) {
        int offset = (page - 1) * size;

        List<CommentDTO> comments = commentMapper.selectCommentsByUser(userId, offset, size);
        long total = commentMapper.countCommentsByUser(userId);

        Pagination pagination = Pagination.builder()
                .currentPage(page)
                .totalPage((int) Math.ceil((double) total / size))
                .totalItems(total)
                .build();

        return CommentListResponse.builder()
                .comments(comments)
                .pagination(pagination)
                .build();
    }
}

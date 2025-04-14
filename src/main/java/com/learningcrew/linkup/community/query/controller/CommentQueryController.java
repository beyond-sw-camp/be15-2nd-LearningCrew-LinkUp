package com.learningcrew.linkup.community.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.community.query.dto.request.CommunitySearchRequest;
import com.learningcrew.linkup.community.query.dto.response.CommentListResponse;
import com.learningcrew.linkup.community.query.service.CommentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "댓글 조회 ", description = "관리자 및 사용자의 댓글 조회 API")
public class CommentQueryController {

    private final CommentQueryService commentQueryService;

    @GetMapping
    @Operation(summary = "전체 댓글 조회 (관리자용)", description = "관리자가 서비스 내에 작성된 모든 댓글을 조회한다.")
    public ResponseEntity<ApiResponse<CommentListResponse>> getAllComments(CommunitySearchRequest request) {
        return ResponseEntity.ok(ApiResponse.success(commentQueryService.getAllComments(request)));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "특정 회원의 댓글 목록 조회", description = "관리자 혹은 특정 사용자가 해당 사용자의 작성 댓글 목록을 조회한다.")
    public ResponseEntity<ApiResponse<CommentListResponse>> getCommentsByUser(
            @PathVariable int userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(ApiResponse.success(commentQueryService.getCommentsByUser(userId, page, size)));
    }
}

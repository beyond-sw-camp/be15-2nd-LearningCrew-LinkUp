package com.learningcrew.linkup.community.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.community.query.dto.request.CommunitySearchRequest;
import com.learningcrew.linkup.community.query.dto.response.PostDetailResponse;
import com.learningcrew.linkup.community.query.dto.response.PostListResponse;
import com.learningcrew.linkup.community.query.service.PostQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "게시글 조회 ", description = "관리자 및 사용자의 게시글 조회 API")
public class PostQueryController {

    private final PostQueryService postQueryService;

    @GetMapping
    @Operation(summary = "전체 게시글 목록 조회 (사용자용)", description = "회원이 서비스 내에 등록된 게시글을 조회한다.")
    public ResponseEntity<ApiResponse<PostListResponse>> getAllPosts(CommunitySearchRequest request) {
        return ResponseEntity.ok(ApiResponse.success(postQueryService.getPosts(request)));
    }

    @GetMapping("/search/{keyword}")
    @Operation(summary = "키워드로 게시글 검색", description = "회원이 서비스 내에 등록된 게시글을 키워드로 검색하여 조회한다.")
    public ResponseEntity<ApiResponse<PostListResponse>> searchPosts(
            @PathVariable String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        CommunitySearchRequest request = new CommunitySearchRequest();
        request.setKeyword(keyword);
        request.setPage(page);
        request.setSize(size);
        return ResponseEntity.ok(ApiResponse.success(postQueryService.getPosts(request)));
    }

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 조회 (댓글 포함)", description = "회원이 게시글의 상세페이지에서 게시글의 내용과 해당 게시글에 작성된 댓글 내역을 조회한다.")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPostDetail(@PathVariable int postId) {
        return ResponseEntity.ok(ApiResponse.success(postQueryService.getPostDetail(postId)));
    }

    @GetMapping("/list")
    @Operation(summary = "전체 게시글 목록 (관리자용, 검색 조건 포함)", description = "관리자가 서비스에 등록되었던 모든 게시글을 조회한다.")
    public ResponseEntity<ApiResponse<PostListResponse>> getPostsForAdmin(CommunitySearchRequest request) {
        return ResponseEntity.ok(ApiResponse.success(postQueryService.getPosts(request)));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "특정 회원의 게시글 목록 (관리자용)", description = "관리자가 특정 회원의 게시글 목록을 조회한다.")
    public ResponseEntity<ApiResponse<PostListResponse>> getPostsByUser(
            @PathVariable int userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(ApiResponse.success(postQueryService.getPostsByUser(userId, page, size)));
    }
}

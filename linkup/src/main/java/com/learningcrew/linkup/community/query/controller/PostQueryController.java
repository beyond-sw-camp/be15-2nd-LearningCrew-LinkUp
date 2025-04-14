package com.learningcrew.linkup.community.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.community.query.dto.request.PostRequest;
import com.learningcrew.linkup.community.query.dto.response.PostDetailResponse;
import com.learningcrew.linkup.community.query.dto.response.PostListResponse;
import com.learningcrew.linkup.community.query.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryService postQueryService;

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<PostListResponse>> getPosts(PostRequest postRequest) {
        PostListResponse response = postQueryService.getPosts(postRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<PostDetailResponse>> getPostDetail(@PathVariable("postId") int postId) {
        PostDetailResponse detailResponse = postQueryService.getPostDetail(postId);
        return ResponseEntity.ok(ApiResponse.success(detailResponse));
    }
}
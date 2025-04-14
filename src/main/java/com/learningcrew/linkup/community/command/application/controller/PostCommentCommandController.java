package com.learningcrew.linkup.community.command.application.controller;

import com.learningcrew.linkup.community.command.application.dto.PostCommentCreateRequestDTO;
import com.learningcrew.linkup.community.command.application.service.PostCommentCommandService;
import com.learningcrew.linkup.community.command.domain.aggregate.PostComment;
import com.learningcrew.linkup.exception.SuccessCode;
import com.learningcrew.linkup.exception.SuccessResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/postcomments")
@Tag(name = "Post Comment", description = "게시글 댓글")
public class PostCommentCommandController {

    private final PostCommentCommandService postCommentCommandService;

    /* 1. 댓글 등록 */
    @PostMapping("")
    @Operation(summary = "게시글 댓글 등록", description = "게시글에 댓글을 등록한다.")
    public ResponseEntity<SuccessResponseMessage> createPostComment(
            @Valid @RequestBody PostCommentCreateRequestDTO postCommentCreateRequestDTO) {

        // 댓글 등록 서비스 호출
        PostComment postComment = postCommentCommandService.createPostComment(postCommentCreateRequestDTO);

        // 성공 응답 메시지 반환
        SuccessResponseMessage response = new SuccessResponseMessage(
                SuccessCode.POST_COMMENT_CREATE_SUCCESS.getHttpStatus(),
                SuccessCode.POST_COMMENT_CREATE_SUCCESS.getMessage(),
                postComment
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* 2. 댓글 삭제 */
    @DeleteMapping("/{postcommentId}")
    @Operation(summary = "게시글 댓글 삭제", description = "게시글에 댓글을 삭제한다.")
    public ResponseEntity<SuccessResponseMessage> deletePostComment(
            @PathVariable BigInteger postcommentId) {

        // 댓글 삭제 서비스 호출
        postCommentCommandService.deletePostComment(postcommentId);

        // 성공 응답 메시지 반환
        SuccessResponseMessage response = new SuccessResponseMessage(
                SuccessCode.POST_COMMENT_UPDATE_SUCCESS.getHttpStatus(),
                SuccessCode.POST_COMMENT_UPDATE_SUCCESS.getMessage(),
                null // 삭제된 댓글은 응답에 포함되지 않음
        );

        return ResponseEntity.ok(response);
    }
}
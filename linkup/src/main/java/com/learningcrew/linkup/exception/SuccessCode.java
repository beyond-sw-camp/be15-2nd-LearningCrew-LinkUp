package com.learningcrew.linkup.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
    @RequiredArgsConstructor
    public enum SuccessCode {

        BASIC_CREATE_SUCCESS(HttpStatus.CREATED, "성공적으로 등록되었습니다."),
        BASIC_UPDATE_SUCCESS(HttpStatus.OK, "성공적으로 수정되었습니다."),
        BASIC_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "성공적으로 삭제되었습니다."),

        POST_CREATE_SUCCESS(HttpStatus.CREATED, "게시글이 성공적으로 등록되었습니다."),
        POST_UPDATE_SUCCESS(HttpStatus.OK, "게시글이 성공적으로 수정되었습니다."),
        POST_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "게시글이 성공적으로 삭제되었습니다."),
        POST_COMMENT_CREATE_SUCCESS(HttpStatus.CREATED, "댓글이 성공적으로 등록되었습니다."),
        POST_COMMENT_UPDATE_SUCCESS(HttpStatus.OK, "댓글이 성공적으로 수정되었습니다."),
        POST_COMMENT_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "댓글이 성공적으로 삭제되었습니다."),

        /* 좋아요 등록 */
        LIKE_SUCCESS(HttpStatus.OK, "좋아요"),

        /* 좋아요 취소 */
        LIKE_DELETE_SUCCESS(HttpStatus.OK, "좋아요 취소"),

        /* 조회 성공 */
        BASIC_GET_SUCCESS(HttpStatus.OK, "조회가 성공적으로 완료되었습니다."),
        POST_GET_SUCCESS(HttpStatus.OK, "게시글 조회가 성공적으로 완료되었습니다."),

        /* 권한 확인 성공 */
        AUTHORITY_CHECK_SUCCESS(HttpStatus.OK, "사용자 권한이 확인되었습니다."),

        /* 회원 탈퇴 */
        USER_DELETION_SUCCESS(HttpStatus.OK, "탈퇴가 성공적으로 처리되었습니다.");


        private final HttpStatus httpStatus;
        private final String message;
    }

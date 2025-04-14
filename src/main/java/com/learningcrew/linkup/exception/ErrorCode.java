package com.learningcrew.linkup.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    /* 400 BAD_REQUEST: 잘못된 요청 */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),

    /* 409 CONFLICT : 서버와의 충돌 */
    NOT_SAVED(HttpStatus.CONFLICT, "Not saved."),

    /* 401 UNAUTHORIZED: 인증되지 않은 사용자의 요청 */
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "Unauthorized."),

    /* 403 FORBIDDEN: 권한이 없는 사용자의 요청 */
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "Forbidden."),

    /* 404 NOT_FOUND: 리소스를 찾을 수 없음 */
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found."),

    /* 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출 */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Not allowed method."),

    /* 409 DUPLICATE_ENTRY: 이미 값이 존재 */
    CONFLICT(HttpStatus.CONFLICT, "Data Conflict"),

    /* 422 UNPROCESSABLE_ENTITY: 요청은 잘 만들어졌지만, 문법 오류로 인하여 따를 수 없습니다. */
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity"),

    /* 500 INTERNAL_SERVER_ERROR: 내부 서버 오류 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server error."),

    // 인증 & 인가 관련
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "JWT 토큰이 만료되었습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "잘못된 JWT 토큰입니다."),
    UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "지원하지 않는 JWT 토큰입니다."),
    EMPTY_JWT(HttpStatus.UNAUTHORIZED, "JWT 클레임이 비어있습니다."),

    // 유저
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    DUPLICATE_CONTACT_NUMBER(HttpStatus.BAD_REQUEST, "이미 존재하는 번호입니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, "아이디 혹은 비밀번호가 올바르지 않습니다."),
    ALREADY_VERIFIED(HttpStatus.BAD_REQUEST, "이미 인증을 거친 계정입니다"),
    INVALID_STATUS(HttpStatus.BAD_REQUEST, "유효하지 않은 상태입니다."),
    INVALID_ROLE(HttpStatus.BAD_REQUEST, "올바르지 않는 권한입니다. "),

    //메일
    SEND_MAIL_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "메일 발송에 실패했습니다."),
    NOT_AUTHORIZED_USER_EMAIL(HttpStatus.BAD_REQUEST, "이메일이 인증되지 않아 회원 가입에 실패했습니다."),
    EXPIRE_VERIFICATION_CODE(HttpStatus.NOT_FOUND, "인증시간이 만료되었습니다. 다시 요청해주세요."),
    TOKEN_ALREADY_SENT(HttpStatus.CONFLICT, "이미 비밀번호 재설정 이메일이 전송되었습니다. 잠시 후 다시 시도해 주세요."),
    INVALID_VERIFICATION_TOKEN(HttpStatus.BAD_REQUEST, "이메일 인증코드가 일치하지 않습니다. "),
    INVALID_TOKEN_TYPE(HttpStatus.BAD_REQUEST, "이메일 타입이 일치하지 않습니다."),


    // 토큰
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "refresh 토큰이 존재하지 않습니다."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "refresh 토큰이 일치하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "refresh 토큰이 만료되었습니다."),

    // 모임
    MEETING_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 모임을 찾을 수 없습니다."),
    MEETING_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 동일한 조건의 모임이 존재합니다."),
    MEETING_PARTICIPATION_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "모임 최대 인원을 초과했습니다."),
    MEETING_ALREADY_JOINED(HttpStatus.BAD_REQUEST, "이미 참여 중인 모임입니다."),
    MEETING_CREATOR_CANNOT_EXIT(HttpStatus.BAD_REQUEST, "모임 생성자는 모임을 나갈 수 없습니다."),

    // 장소 예약
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "예약 정보를 찾을 수 없습니다."),
    RESERVATION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 예약이 존재합니다."),
    RESERVATION_TIME_CONFLICT(HttpStatus.CONFLICT, "예약 시간이 중복됩니다."),
    RESERVATION_CANNOT_CANCEL(HttpStatus.BAD_REQUEST, "현재 예약은 취소할 수 없습니다."),
    PLACE_NOT_FOUND(HttpStatus.BAD_REQUEST, "현재 예약은 취소할 수 없습니다."),
    INVALID_OPERATION_TIME(HttpStatus.BAD_REQUEST, "현재 예약은 취소할 수 없습니다."),

    // 신고
    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "신고 내역을 찾을 수 없습니다."),
    REPORT_ALREADY_EXISTS(HttpStatus.CONFLICT, "해당 항목은 이미 신고되었습니다."),
    REPORT_ALREADY_SUBMITTED(HttpStatus.BAD_REQUEST, "이미 신고한 사용자입니다."),
    REPORT_REASON_REQUIRED(HttpStatus.BAD_REQUEST, "신고 사유는 필수입니다."),

    // 제재
    PENALTY_NOT_FOUND(HttpStatus.NOT_FOUND, "제재 이력을 찾을 수 없습니다."),
    PENALTY_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 제재된 콘텐츠입니다."),
    PENALTY_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "제재 정보를 저장하는 데 실패했습니다."),
    PENALTY_CANCEL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "제재를 철회하는 데 실패했습니다."),
    PENALTY_ALREADY_CONFIRMED(HttpStatus.BAD_REQUEST, "이미 확정된 제재입니다."),
    PENALTY_ALREADY_CANCELED(HttpStatus.BAD_REQUEST, "이미 철회된 제재입니다."),

    // 이의 제기
    OBJECTION_NOT_FOUND(HttpStatus.NOT_FOUND, "이의 제기 내역을 찾을 수 없습니다."),
    OBJECTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 이의 제기를 제출한 제재입니다."),
    OBJECTION_ALREADY_PROCESSED(HttpStatus.BAD_REQUEST, "이미 처리된 이의 제기입니다."),
    OBJECTION_REASON_REQUIRED(HttpStatus.BAD_REQUEST, "이의 제기 사유는 필수입니다."),
    OBJECTION_UNAUTHORIZED(HttpStatus.FORBIDDEN, "이의 제기를 제출할 권한이 없습니다."),

    // 블랙리스트
    ALREADY_BLACKLISTED(HttpStatus.CONFLICT, "이미 블랙리스트에 등록된 사용자입니다."),
    BLACKLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "블랙리스트에 등록되지 않은 사용자입니다."),
    CANNOT_BLACKLIST_ADMIN(HttpStatus.FORBIDDEN, "관리자는 블랙리스트에 등록할 수 없습니다."),

    // 알림
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다."),
    NOTIFICATION_ALREADY_READ(HttpStatus.BAD_REQUEST, "이미 읽은 알림입니다."),

    // 커뮤니티
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다."),
    UNAUTHORIZED_POST_EDIT(HttpStatus.FORBIDDEN, "해당 게시글 수정 권한이 없습니다."),
    UNAUTHORIZED_COMMENT_DELETE(HttpStatus.FORBIDDEN, "해당 댓글 삭제 권한이 없습니다."),
    TITLE_EMPTY(HttpStatus.BAD_REQUEST, "게시글 제목은 비워둘 수 없습니다."),
    CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "게시글 내용은 비워둘 수 없습니다."),
    IMAGE_URLS_EMPTY(HttpStatus.BAD_REQUEST, "이미지 URL 목록은 비워둘 수 없습니다."),


    // 후기
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "후기를 찾을 수 없습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 후기를 작성하셨습니다."),
    UNAUTHORIZED_REVIEW_EDIT(HttpStatus.FORBIDDEN, "해당 후기를 수정할 수 없습니다."),
    INVALID_REVIEW_SCORE(HttpStatus.BAD_REQUEST, "유효하지 않은 평점입니다.");


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
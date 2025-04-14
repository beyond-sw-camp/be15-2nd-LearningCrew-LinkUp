package com.learningcrew.linkup.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status;
    private final String errorCode;
    private final String message;

    @Builder
    public ErrorResponse(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    /* ErrorCode만(HTTP Status, Errorcode 에러코드명(상수명), 에러 메시지)*/
    public static ErrorResponse of(ErrorCode code) {
        return ErrorResponse.builder()
                .status(code.getStatus().value())
                .errorCode(code.name())
                .message(code.getMessage())
                .build();
    }
    /* ErrorCode(HTTP Status, Errorcode 에러코드명(상수명) + 커스텀 에러메시지 */
    public static ErrorResponse of(ErrorCode code, String customMessage) {
        return ErrorResponse.builder()
                .status(code.getStatus().value())
                .errorCode(code.name())
                .message(customMessage)
                .build();
    }
}
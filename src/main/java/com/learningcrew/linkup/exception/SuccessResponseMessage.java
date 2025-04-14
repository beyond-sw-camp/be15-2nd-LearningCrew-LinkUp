package com.learningcrew.linkup.exception;

import ch.qos.logback.core.joran.action.Action;
import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SuccessResponseMessage {

    private final HttpStatus status;
    private int statusCode;
    private String success;
    private String message;
    private Object data;

    public SuccessResponseMessage() {
        this.status = HttpStatus.OK;  // 기본 상태로 초기화
    }

    public SuccessResponseMessage(SuccessCode successCode) {
        this.status = successCode.getHttpStatus();
        this.statusCode = successCode.getHttpStatus().value();
        this.success = successCode.getHttpStatus().name();
        this.message = successCode.getMessage();
    }

    public SuccessResponseMessage(HttpStatus httpStatus, String message, Object data) {
        this.status = httpStatus;
        this.statusCode = httpStatus.value();
        this.success = httpStatus.name();
        this.message = message;
        this.data = data;}

    public SuccessResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
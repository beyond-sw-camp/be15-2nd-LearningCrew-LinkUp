package com.learningcrew.linkup.exception.security;

import com.learningcrew.linkup.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomJwtException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomJwtException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

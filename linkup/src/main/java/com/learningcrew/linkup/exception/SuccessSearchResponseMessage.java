package com.learningcrew.linkup.exception;

import com.learningcrew.linkup.community.command.domain.aggregate.Post;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class SuccessSearchResponseMessage<T> extends SuccessResponseMessage {

    private List<T> data;
    private T data2;

    public SuccessSearchResponseMessage() {

    }

    public SuccessSearchResponseMessage(
            SuccessCode successCode, List<T> data) {
        super(successCode);
        this.data = data;
    }

    public SuccessSearchResponseMessage(
            SuccessCode successCode, T data2) {
        super(successCode);
        this.data2 = data2;
    }

    public SuccessSearchResponseMessage(HttpStatus httpStatus, String message, Post savedPost) {
    }
}
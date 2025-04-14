package com.learningcrew.linkup.community.query.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PostRequest {
    private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private boolean isNotice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean isDeleted;

    private Integer page = 1;
    private Integer size = 10;

    public int getOffset(){
        return (page - 1) * size;
    }

    public int getLimit(){
        return size;
    }


    public Object getImageUrls() {
        return this.getImageUrls();
    }

    public Object getIsNotice() {
        return this.isNotice;
    }

    public Object getIsdeleted() {
        return this.getIsdeleted();
    }

    public Object isDeleted() {
        return this.getIsdeleted();
    }
}
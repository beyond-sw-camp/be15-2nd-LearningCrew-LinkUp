package com.learningcrew.linkup.community.command.application.dto;

import com.learningcrew.linkup.community.command.domain.aggregate.PostImage;
import com.learningcrew.linkup.community.command.domain.constants.PostIsNotice;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostUpdateRequestDTO {


    private Integer postId;

    private Integer userId;

    private String title;

    private String content;

    private List<PostImage> images = new ArrayList<>();

    private boolean postIsNotice;

    public boolean postIsNotice() {
        return this.postIsNotice;

    }
}





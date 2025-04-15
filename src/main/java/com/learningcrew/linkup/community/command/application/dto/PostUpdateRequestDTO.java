package com.learningcrew.linkup.community.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningcrew.linkup.community.command.domain.aggregate.PostImage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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



    @Min(1)
    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String keywords;
    private String postIsDeleted;
    private String postIsNotice;

    @JsonProperty("imageUrls")
    private List<String> imageUrls;
    public PostUpdateRequestDTO(Integer userId, Integer postId, String title, String content, String postIsDeleted, String postIsNotice, List<String> imageUrls) {
        this.userId = userId;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postIsDeleted = postIsDeleted;
        this.postIsNotice = postIsNotice;
        this.imageUrls = imageUrls;
    }

    private Integer postId;



    public String postIsNotice() {
        return this.postIsNotice;

    }
}





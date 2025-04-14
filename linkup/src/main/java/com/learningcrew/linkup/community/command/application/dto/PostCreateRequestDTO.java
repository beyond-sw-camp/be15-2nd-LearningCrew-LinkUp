package com.learningcrew.linkup.community.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostCreateRequestDTO {

    @Min(1)
    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Boolean postIsDeleted;
    private Boolean postIsNotice;

    @JsonProperty("imageUrls")
    private List<String> imageUrls;

    public PostCreateRequestDTO(Integer userId, Integer postId, String title, String content, Object deleted, Object isNotice, Object imageUrls) {
    }
}

package com.learningcrew.linkup.community.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostImageResponse {
    private List<String> imageUrls;
}
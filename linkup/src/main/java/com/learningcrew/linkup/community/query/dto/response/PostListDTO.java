package com.learningcrew.linkup.community.query.dto.response;

import com.learningcrew.linkup.community.command.application.dto.PostCreateRequestDTO;
import com.learningcrew.linkup.community.query.dto.request.PostRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PostListDTO {

    private List<PostCreateRequestDTO> posts;  // 여러 게시글을 담는 리스트

    public PostListDTO() {
        // 기본 생성자 추가
    }

    public PostListDTO(List<PostCreateRequestDTO> posts) {
        this.posts = posts;
    }

    // PostRequest 리스트를 PostCreateRequestDTO 리스트로 변환하는 메서드
    public static PostListDTO fromPostRequests(List<PostRequest> postRequests) {
        List<PostCreateRequestDTO> dtoList = new ArrayList<>();

        for (PostRequest postRequest : postRequests) {
            PostCreateRequestDTO dto = new PostCreateRequestDTO(
                    postRequest.getUserId(),
                    postRequest.getPostId(),
                    postRequest.getTitle(),
                    postRequest.getContent(),
                    postRequest.isDeleted(),
                    postRequest.getIsNotice(),
                    postRequest.getImageUrls()
            );
            dtoList.add(dto);
        }

        return new PostListDTO(dtoList);
    }
}

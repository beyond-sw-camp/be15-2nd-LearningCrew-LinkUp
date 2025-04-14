package com.learningcrew.linkup.community.query.dto.response;

import com.learningcrew.linkup.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PostListResponse {

    private final List<PostDTO> posts;
    private final Pagination pagination;

    private int currentPage;
    private int totalPages;
    private int totalPosts;



//    public PostListResponse(List<PostRequest> posts, int currentPage, int totalPages, int totalPosts) {
//        this.posts = posts;
//        this.currentPage = currentPage;
//        this.totalPages = totalPages;
//        this.totalPosts = totalPosts;
//    }

    private int postId;
    private String title;
    private LocalDateTime postCreatedAt;

}

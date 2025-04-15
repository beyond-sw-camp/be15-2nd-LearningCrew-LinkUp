package com.learningcrew.linkup.community.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String imageUrl;

    public void setPostId(int postId) {
        Post post = new Post();
        post.setPostId(postId);
        this.post = post;
    }



    // 주 이미지 여부를 나타내는 필드
    @Column(name = "is_main")
    private boolean isMain;

    // 이미지 URL에서 파일명을 추출하는 메서드
    public String getFilename() {
        // 이미지 URL에서 파일명을 추출 (예: "main_image.jpg")
        int lastSlashIndex = imageUrl.lastIndexOf('/');
        if (lastSlashIndex != -1 && lastSlashIndex < imageUrl.length() - 1) {
            return imageUrl.substring(lastSlashIndex + 1);
        }
        return imageUrl; // 파일명이 없으면 URL 자체 반환
    }

    public void setMain(boolean isMain) {
        this.isMain = isMain;
    }
}
package com.learningcrew.linkup.community.command.domain.aggregate;

import com.learningcrew.linkup.community.command.domain.constants.PostIsDeleted;
import com.learningcrew.linkup.community.command.domain.constants.PostIsNotice;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community_post")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    private int userId;

    private String title;

    private String content;

    @Column(name = "is_deleted")
    private String isDeleted = "N";

    @Enumerated(EnumType.STRING)
    @Column(name = "is_notice")
    private PostIsNotice postIsNotice = PostIsNotice.N;

    @Column(name = "created_at")
    private LocalDateTime postCreatedAt;

    @Column(name = "updated_at")
    private LocalDateTime postUpdatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime postDeletedAt;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;

//    // 게시글과 댓글 관계 설정@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<PostComment> postComments = new ArrayList<>();

    // 게시글과 이미지 관계 설정 (게시글에 여러 이미지가 있을 수 있음)(mappedBy = "post", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostImage> images = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.postCreatedAt = LocalDateTime.now();
        this.postUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {

        this.postUpdatedAt = LocalDateTime.now();
    }


    public void updatePostDetails(Integer userId, String title, String content, String isNotice) {
        this.userId = userId;  // 혹시 다른 타입 변환이 필요할 수 있습니다.
        this.title = title;
        this.content = content;
        this.postIsNotice = PostIsNotice.valueOf(isNotice);
    }

    public void addImage(PostImage image) {
        if (image != null) {
            this.images.add(image);
            image.setPost(this);
        }
    }

    public void setMainImageUrl(String mainImageFilename) {
        for (PostImage image : images) {
            if (image.getFilename().equals(mainImageFilename)) {
                image.setMain(true);  // 주 이미지 설정 (PostImage 클래스에 `setMain` 메서드가 필요)
                break;
            }
        }
    }

    public void setIsDelete(String isDeleted) {
        this.isDeleted = isDeleted;
    }



}


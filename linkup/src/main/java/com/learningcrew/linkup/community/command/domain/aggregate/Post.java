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

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    private PostIsDeleted postIsDeleted = PostIsDeleted.N;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_notice")
    private PostIsNotice postIsNotice = PostIsNotice.N;

    @Column(name = "created_at")
    private LocalDateTime postCreatedAt;

    @Column(name = "updated_at")
    private LocalDateTime postUpdatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime postDeletedAt;

    // 게시글과 댓글 관계 설정@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @OneToMany
    private List<PostComment> postComments = new ArrayList<>();

    // 게시글과 이미지 관계 설정 (게시글에 여러 이미지가 있을 수 있음)(mappedBy = "post", cascade = CascadeType.ALL)
    @OneToMany
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

    public void updatePostDetails(int postId, int userId, String title, String content) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }



    public void updatePostDetails(String title, String content, boolean b) {

    }

    public void addImage(PostImage image) {
    }
}


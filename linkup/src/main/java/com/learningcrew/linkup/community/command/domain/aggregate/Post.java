package com.learningcrew.linkup.community.command.domain.aggregate;

//import com.learningcrew.linkup.common.Image;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "community_post")
@EntityListeners(AuditingEntityListener.class)  // Auditing 활성화 -> CreateDate, LastModifiedDate 작동
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "title", nullable = false, length = 30)
    private String postTitle;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String postContent;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "ENUM('Y','N') DEFAULT 'N'")
    private String postIsDeleted = "N";

    @Column(name = "is_notice", nullable = false, columnDefinition = "ENUM('Y','N') DEFAULT 'N'")
    private String postIsNotice = "N";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime postCreatedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime postUpdatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime postDeletedAt;

    @PrePersist
    protected void onCreate() {
        this.postCreatedAt = LocalDateTime.now();
        this.postUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.postUpdatedAt = LocalDateTime.now();
    }


    public void updatePostDetails(int postId, int userId, String postTitle, String postContent) {
        this.postId = postId;
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }


    // 게시글과 이미지를 연결하는 관계
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Image> images;


}


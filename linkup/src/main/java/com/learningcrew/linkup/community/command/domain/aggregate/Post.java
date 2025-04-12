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
    private int postId;

    private int userId;

    private String postTitle;

    private String postContent;

    private String postIsDeleted = "N";

    private String postIsNotice = "N";

    private LocalDateTime postCreatedAt;

    private LocalDateTime postUpdatedAt;

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


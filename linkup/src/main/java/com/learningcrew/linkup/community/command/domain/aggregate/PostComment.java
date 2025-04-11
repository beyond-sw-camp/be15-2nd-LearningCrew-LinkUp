package com.learningcrew.linkup.community.command.domain.aggregate;

import com.learningcrew.linkup.community.command.application.dto.PostCommentUpdateRequestDTO;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "community_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long postCommentId; // BIGINT에 대응되는 자료형은 long

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    private int postId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private int userId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String postCommentContent;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "ENUM('Y', 'N') DEFAULT 'N'")
    private String postCommentIsDeleted = "N";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime postCommentCreatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime postCommentDeletedAt;

    @PrePersist
    protected void onCreate() {
        this.postCommentCreatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if ("Y".equals(postCommentIsDeleted)) {
            this.postCommentDeletedAt = LocalDateTime.now();
        }
    }

    public void updatePostComment(PostCommentUpdateRequestDTO postCommentUpdateRequestDTO) {
        if (postCommentUpdateRequestDTO.getCommentContent() == null || postCommentUpdateRequestDTO.getCommentContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }
        this.postCommentContent = postCommentUpdateRequestDTO.getCommentContent();
    }

//    public int getPostCommentUserId() {
//        return user.getUserId();
//    }

//    @OneToMany(mappedBy = "postComment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)  // 댓글에 달린 좋아요
//    private List<PostCommentLike> likes;  // 좋아요 목록



}
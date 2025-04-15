package com.learningcrew.linkup.community.command.domain.aggregate;

import com.learningcrew.linkup.community.command.domain.constants.PostCommentIsDeleted;
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
    private BigInteger postCommentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private int userId;

    @Column(name = "content")
    private String commentContent;

    @Setter
    @Column(name = "is_deleted")
    @Builder.Default
    private String commentIsDeleted = "N";

    @Column(name = "created_at")
    private LocalDateTime postCommentCreatedAt;

    @Setter
    @Column(name = "deleted_at")
    private LocalDateTime postCommentDeletedAt;

    public PostComment(Post post, int userId, String commentContent) {
        this.post = post;
        this.userId = userId;
        this.commentContent = commentContent;
    }

    @PrePersist
    protected void onCreate() {
        this.postCommentCreatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if ("Y".equals(commentIsDeleted) && postCommentDeletedAt == null) {
            this.postCommentDeletedAt = LocalDateTime.now();
        }
    }

    public void softDelete() {
        this.commentIsDeleted = "Y";
        this.postCommentDeletedAt = LocalDateTime.now();
    }

    public void setIsDelete(String commentIsDeleted) {
        this.commentIsDeleted = commentIsDeleted;
    }



}
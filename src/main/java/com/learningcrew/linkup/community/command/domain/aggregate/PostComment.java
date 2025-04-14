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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String commentContent;

    @Setter
    @Column(name = "is_deleted")
    @Enumerated(EnumType.STRING)
    private PostCommentIsDeleted postCommentIsDeleted = PostCommentIsDeleted.N;

    @Column(name = "created_at")
    private LocalDateTime postCommentCreatedAt;

    @Setter
    @Column(name = "deleted_at")
    private LocalDateTime postCommentDeletedAt;

    public PostComment(Post post, User user, String commentContent) {
        this.post = post;
        this.user = user;
        this.commentContent = commentContent;
    }

    @PrePersist
    protected void onCreate() {
        this.postCommentCreatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (PostCommentIsDeleted.Y.equals(postCommentIsDeleted) && postCommentDeletedAt == null) {
            this.postCommentDeletedAt = LocalDateTime.now();
        }
    }

    public void softDelete() {
        this.postCommentIsDeleted = PostCommentIsDeleted.Y;
    }

}
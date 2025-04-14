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
    private BigInteger postCommentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String postCommentContent;

    @Enumerated(EnumType.STRING)
    private PostCommentIsDeleted postCommentIsDeleted = PostCommentIsDeleted.N;

    private LocalDateTime postCommentCreatedAt;

    private LocalDateTime postCommentDeletedAt;

    public PostComment(Post post, User user, String postCommentContent) {
    }

    @PrePersist
    protected void onCreate() {
        this.postCommentCreatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (PostCommentIsDeleted.N.equals(postCommentIsDeleted)) {
            this.postCommentDeletedAt = LocalDateTime.now();
        }
    }

    public void setPostCommentIsDeleted(String postCommentIsDeleted) {
    }

    public void setPostCommentDeletedAt(LocalDateTime now) {
    }


}
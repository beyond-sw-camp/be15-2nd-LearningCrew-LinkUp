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
    private BigInteger postCommentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String postCommentContent;

    private String postCommentIsDeleted = "N";

    private LocalDateTime postCommentCreatedAt;

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

    public int getPostCommentUserId() {
        return user.getUserId();
    }

//    @OneToMany(mappedBy = "postComment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)  // 댓글에 달린 좋아요
//    private List<PostCommentLike> likes;  // 좋아요 목록



}
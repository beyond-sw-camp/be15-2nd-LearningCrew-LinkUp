package com.learningcrew.linkup.community.command.domain.aggregate;

import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment_like")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postCommentLikeId;  // 좋아요 ID (자동 증가)

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private PostComment postComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private PostCommentLike(User user, PostComment postComment) {
        this.user = user;
        this.postComment = postComment;
    }

//    public static PostCommentLike create(User user, PostComment postComment) {
//        return new PostCommentLike(user, postComment);
//    }
}





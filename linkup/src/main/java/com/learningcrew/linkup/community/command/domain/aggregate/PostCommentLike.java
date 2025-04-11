package com.learningcrew.linkup.community.command.domain.aggregate;

import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("commentId")
//    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false)
    private long commentId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("userId")
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private int userId;

    private PostCommentLike(int userId, long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }

//    public static PostCommentLike create(User user, PostComment postComment) {
//        return new PostCommentLike(user, postComment);
//    }
}





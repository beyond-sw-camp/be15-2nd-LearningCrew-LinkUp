package com.learningcrew.linkup.community.command.domain.aggregate;

import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_like")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postLikeId;  // 기본 키 (자동 증가)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    private Post post;  // 게시물 (Many-to-One 관계)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;  // 사용자 (Many-to-One 관계)

}

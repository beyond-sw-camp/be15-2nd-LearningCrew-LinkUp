package com.learningcrew.linkup.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

// 사용자 제재 이력
@Entity
@Table(name = "user_penalty_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPenaltyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalty_id")
    private Integer id;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "penalty_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PenaltyType penaltyType;

    @Column(name = "reason", nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Builder.Default
    @Column(name = "is_active", columnDefinition = "ENUM('Y','N')")
    private String isActive = "Y";

}

package com.learningcrew.linkup.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// 이의 제기
@Entity
@Table(name = "objection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Objection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objection_id")
    private Integer id;

    @Column(name = "penalty_id", nullable = false)
    private Integer penaltyId;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    @Column(name = "reason", nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}
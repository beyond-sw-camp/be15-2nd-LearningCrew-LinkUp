package com.learningcrew.linkup.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

// 신고 유형
@Entity
@Table(name = "report_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_type_id")
    private Byte id;

    @Column(name = "report_type", nullable = false, unique = true, length = 20)
    private String type;

    @Column(name = "report_level", nullable = false)
    private Byte level;
}

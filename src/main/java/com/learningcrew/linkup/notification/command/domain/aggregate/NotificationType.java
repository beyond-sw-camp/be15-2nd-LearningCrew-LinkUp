package com.learningcrew.linkup.notification.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notification_type")
public class NotificationType {

    @Id
    @Column(name = "notification_type_id")  // DDL에 맞춰 실제 PK 컬럼 이름으로 수정
    private Integer id;

    // 알림 제목 (예: FRIEND_REQUEST)
    @Column(name = "notification_type", nullable = false)
    private String notificationType;

    // 알림 템플릿 (내용)
    @Column(name = "notification_template", nullable = false)
    private String notificationTemplate;
}
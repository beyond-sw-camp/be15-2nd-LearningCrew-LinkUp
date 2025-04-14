package com.learningcrew.linkup.notification.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer id;

    // NotificationType의 notification_type 값이 저장될 제목
    @Column(nullable = false, name = "title")
    private String title;

    // NotificationType의 notification_template 값이 저장될 내용
    @Column(nullable = false, name = "content")
    private String content;

    // 알림 수신자 ID
    @Column(name = "receiver_id", nullable = false)
    private Integer receiverId;

    // 읽음 상태 (false이면 미확인)
    @Column(name = "is_read", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationReadStatus isRead = NotificationReadStatus.N;

    // 알림 생성 시간 (자동 설정)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // 도메인 타입 ID (foreign key: domain_type 테이블)
    @Column(name = "domain_type_id", nullable = false)
    private Integer domainTypeId;

    // 알림 타입 ID (foreign key: notification_type 테이블)
    @Column(name = "notification_type_id", nullable = false)
    private Integer notificationTypeId;

    public Notification(String title, String content, Integer receiverId, Integer domainTypeId, Integer notificationTypeId) {
        this.title = title;
        this.content = content;
        this.receiverId = receiverId;
        this.domainTypeId = domainTypeId;
        this.notificationTypeId = notificationTypeId;
    }

    public void markAsRead() {
        this.isRead = NotificationReadStatus.Y;
    }
}
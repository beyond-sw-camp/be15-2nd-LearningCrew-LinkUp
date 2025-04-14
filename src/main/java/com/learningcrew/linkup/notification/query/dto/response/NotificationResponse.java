
package com.learningcrew.linkup.notification.query.dto.response;

import com.learningcrew.linkup.notification.command.domain.aggregate.NotificationReadStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private Integer id;
    private String title;              // 알림 제목 (NotificationType의 notification_type 값)
    private String content;            // 알림 내용 (NotificationType의 notification_template 값)
    private Integer receiverId;
    private NotificationReadStatus isRead;
    private LocalDateTime createdAt;
    private Integer domainTypeId;         // 도메인 타입 ID
    private Integer notificationTypeId;   // 알림 타입 ID
}
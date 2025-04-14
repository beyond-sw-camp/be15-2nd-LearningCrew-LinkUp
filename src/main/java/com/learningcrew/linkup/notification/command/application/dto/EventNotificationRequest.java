package com.learningcrew.linkup.notification.command.application.dto;

import lombok.Data;

@Data
public class EventNotificationRequest {

    private Integer receiverId;           // 알림 받을 사용자 ID
    private Integer domainTypeId;         // 도메인 ID
    private Integer notificationTypeId;   // 알림 유형 ID

    // ✅ 선택적으로 사용하는 필드 (템플릿 바인딩 알림일 경우에만 사용)
    private String title;
    private String content;
}
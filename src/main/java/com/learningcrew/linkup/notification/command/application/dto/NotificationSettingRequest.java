package com.learningcrew.linkup.notification.command.application.dto;

import lombok.Data;

@Data
public class NotificationSettingRequest {
    // true이면 알림 전송을 허용, false이면 차단
    private boolean enabled;
    private Integer notificationTypeId;
}
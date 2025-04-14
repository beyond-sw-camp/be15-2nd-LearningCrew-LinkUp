package com.learningcrew.linkup.notification.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkNotificationReadResponse {
    private Integer notificationId;
    private boolean isRead;
}
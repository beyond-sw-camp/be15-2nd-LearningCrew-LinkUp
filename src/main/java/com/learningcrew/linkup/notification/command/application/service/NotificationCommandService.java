package com.learningcrew.linkup.notification.command.application.service;

import com.learningcrew.linkup.notification.command.application.dto.CreateNotificationResponse;
import com.learningcrew.linkup.notification.command.application.dto.EventNotificationRequest;
import com.learningcrew.linkup.notification.command.application.dto.MarkNotificationReadResponse;
import com.learningcrew.linkup.notification.command.application.dto.NotificationSettingRequest;

public interface NotificationCommandService {
    /**
     * 이벤트 기반 알림을 생성 및 전송합니다.
     */
    CreateNotificationResponse sendEventNotification(EventNotificationRequest request);

    /**
     * 사용자의 알림 전송 설정(동의/차단)을 업데이트합니다.
     */
    void updateNotificationSetting(Integer userId, NotificationSettingRequest request);

    /**
     * 특정 알림을 읽음 상태로 업데이트합니다.
     */
    MarkNotificationReadResponse markNotificationAsRead(Integer notificationId);
}
package com.learningcrew.linkup.notification.command.domain.service;

import com.learningcrew.linkup.notification.command.domain.aggregate.Notification;

public interface NotificationDomainService {
    // 알림에 대해 추가 비즈니스 로직을 처리 (예, 로깅, 상태 체크 등)
    void processNotification(Notification notification);
}
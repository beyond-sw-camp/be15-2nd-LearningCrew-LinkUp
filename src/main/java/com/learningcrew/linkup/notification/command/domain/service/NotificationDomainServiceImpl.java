package com.learningcrew.linkup.notification.command.domain.service;

import com.learningcrew.linkup.notification.command.domain.aggregate.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationDomainServiceImpl implements NotificationDomainService {
    @Override
    public void processNotification(Notification notification) {
        // notification.getTitle()은 NotificationType 테이블의 notification_type 값을 반영합니다.
        log.info("Processing notification with title '{}' for receiver {}", notification.getTitle(), notification.getReceiverId());
    }
}
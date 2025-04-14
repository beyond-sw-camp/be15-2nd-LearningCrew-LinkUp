package com.learningcrew.linkup.notification.command.infrastructure;

import com.learningcrew.linkup.notification.command.domain.aggregate.Notification;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationSseService {

    SseEmitter connect(Integer userId);

    // 클라이언트 등록 (SSE 연결)
    void registerEmitter(SseEmitter emitter);
    // 알림 객체를 SSE를 통해 클라이언트에 전파
    void pushNotification(Notification notification);

}
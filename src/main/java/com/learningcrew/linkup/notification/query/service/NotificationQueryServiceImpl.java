package com.learningcrew.linkup.notification.query.service;

import com.learningcrew.linkup.notification.query.dto.response.NotificationResponse;
import com.learningcrew.linkup.notification.query.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationMapper notificationMapper;
    
    @Override
    public List<NotificationResponse> getRecentNotifications(Integer userId) {
        // MyBatis 매퍼를 통해 최신 5건의 알림을 조회합니다.
        return notificationMapper.findRecentNotificationsByUserId(userId);
    }
}
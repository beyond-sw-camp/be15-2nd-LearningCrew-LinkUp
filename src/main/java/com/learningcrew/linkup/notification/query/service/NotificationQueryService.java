package com.learningcrew.linkup.notification.query.service;

import com.learningcrew.linkup.notification.query.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationQueryService {
    // 특정 사용자의 최신 알림 목록(최대 5건)을 조회합니다.
    List<NotificationResponse> getRecentNotifications(Integer userId);
}
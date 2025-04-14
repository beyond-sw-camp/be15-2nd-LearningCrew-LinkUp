package com.learningcrew.linkup.notification.command.domain.repository;

import com.learningcrew.linkup.notification.command.domain.aggregate.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    // 예: 특정 수신자의 최근 5건 알림 조회 메소드
    List<Notification> findTop5ByReceiverIdOrderByCreatedAtDesc(Integer receiverId);
}
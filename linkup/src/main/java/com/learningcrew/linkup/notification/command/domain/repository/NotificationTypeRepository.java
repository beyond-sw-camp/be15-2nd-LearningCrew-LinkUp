package com.learningcrew.linkup.notification.command.domain.repository;

import com.learningcrew.linkup.notification.command.domain.aggregate.Notification;
import com.learningcrew.linkup.notification.command.domain.aggregate.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer> {
}
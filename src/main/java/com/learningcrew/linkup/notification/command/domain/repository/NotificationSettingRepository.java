package com.learningcrew.linkup.notification.command.domain.repository;

import com.learningcrew.linkup.notification.command.domain.aggregate.NotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Integer> {

    Optional<NotificationSetting> findByUserIdAndNotificationTypeId(Integer userId, Integer notificationTypeId);
}
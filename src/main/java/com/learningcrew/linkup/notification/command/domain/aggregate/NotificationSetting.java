package com.learningcrew.linkup.notification.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "notification_setting")
public class NotificationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_setting_id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "notification_type_id", nullable = false)
    private Integer notificationTypeId;

    @Column(name = "is_enabled", nullable = false, columnDefinition = "ENUM('Y','N')")
    private String isEnabled;


    public boolean isEnabled() {
        return "Y".equals(this.isEnabled);
    }
}
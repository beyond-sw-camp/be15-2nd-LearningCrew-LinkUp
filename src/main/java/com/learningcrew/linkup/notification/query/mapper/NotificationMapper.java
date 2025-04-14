package com.learningcrew.linkup.notification.query.mapper;

import com.learningcrew.linkup.notification.query.dto.response.NotificationResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotificationMapper {

    /**
     * 특정 사용자의 최신 5건의 알림을 조회하는 SQL 매핑.
     */
    @Select("SELECT " +
                "notification_id as id, " +
                "title, " +
                "content, " +
                "receiver_id as receiverId, " +
                "is_read as isRead, " +
                "created_at as createdAt, " +
                "domain_type_id as domainTypeId, " +
                "notification_type_id as notificationTypeId " +
            "FROM notification " +
            "WHERE receiver_id = #{userId} " +
            "ORDER BY created_at DESC LIMIT 5")
    List<NotificationResponse> findRecentNotificationsByUserId(@Param("userId") Integer userId);
}
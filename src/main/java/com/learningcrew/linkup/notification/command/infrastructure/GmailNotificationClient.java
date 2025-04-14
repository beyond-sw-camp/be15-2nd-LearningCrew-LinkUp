package com.learningcrew.linkup.notification.command.infrastructure;

public interface GmailNotificationClient {
    void sendEmailNotification(String to, String subject, String body);
}
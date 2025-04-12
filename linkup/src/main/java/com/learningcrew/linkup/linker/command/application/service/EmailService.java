package com.learningcrew.linkup.linker.command.application.service;

public interface EmailService {
    void sendVerificationCode(int userId, String email, String userName);
}


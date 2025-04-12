package com.learningcrew.linkup.linker.command.application.service;

public interface VerificationTokenService {
    String createAndSaveToken(int userId, String email, String tokenType);
}

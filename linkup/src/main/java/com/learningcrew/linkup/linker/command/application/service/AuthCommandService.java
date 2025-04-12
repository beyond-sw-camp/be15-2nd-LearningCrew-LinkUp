package com.learningcrew.linkup.linker.command.application.service;

import com.learningcrew.linkup.linker.command.application.dto.request.LoginRequest;
import com.learningcrew.linkup.linker.command.application.dto.response.TokenResponse;

public interface AuthCommandService {

    TokenResponse login(LoginRequest request);

    TokenResponse refreshToken(String providedRefreshToken);

    void logout(String refreshToken);

    void verifyEmail(String tokenCode);
}

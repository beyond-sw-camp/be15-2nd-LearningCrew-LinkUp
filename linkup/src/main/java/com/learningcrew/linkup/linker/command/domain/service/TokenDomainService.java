package com.learningcrew.linkup.linker.command.domain.service;

import com.learningcrew.linkup.linker.command.domain.aggregate.User;

public interface TokenDomainService {
    String generateToken(User user);
    String generateRefreshToken(User user);
    void saveRefreshToken(String email, String refreshToken);
}

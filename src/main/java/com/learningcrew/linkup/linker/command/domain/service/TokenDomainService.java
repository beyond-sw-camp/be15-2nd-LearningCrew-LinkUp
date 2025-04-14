package com.learningcrew.linkup.linker.command.domain.service;

import com.learningcrew.linkup.linker.command.domain.aggregate.RefreshToken;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.aggregate.VerificationToken;
import com.learningcrew.linkup.linker.command.domain.repository.RefreshtokenRepository;
import com.learningcrew.linkup.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenDomainService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshtokenRepository refreshtokenRepository;

    public String generateToken(User user) {
        return jwtTokenProvider.createToken(user.getEmail(), user.getRole().getRoleName());
    }

    public String generateRefreshToken(User user) {
        return jwtTokenProvider.createRefreshToken(user.getEmail(), user.getRole().getRoleName());
    }

    public void saveRefreshToken(String email, String refreshToken) {
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .userEmail(email)
                .token(refreshToken)
                .expiryDate(new Date(System.currentTimeMillis() + jwtTokenProvider.getRefreshTokenExpiration()))
                .build();

        refreshtokenRepository.save(refreshTokenEntity);
    }

    public void delete(VerificationToken verificationToken) {
    }
}

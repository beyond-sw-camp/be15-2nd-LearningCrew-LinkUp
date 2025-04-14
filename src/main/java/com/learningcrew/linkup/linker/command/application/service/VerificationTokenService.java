package com.learningcrew.linkup.linker.command.application.service;

import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.aggregate.VerificationToken;
import com.learningcrew.linkup.linker.command.domain.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;
    // 인증 코드 생성 및 저장
    public String createAndSaveToken(int userId, String email, String tokenType) {
        String code = makeVerificationCode();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(10);

        VerificationToken token = VerificationToken.builder()
                .userId(userId)
                .email(email)
                .code(code)
                .expiryDate(expiry)
                .tokenType(tokenType)
                .build();

        verificationTokenRepository.save(token);
        return code;
    }

    // 인증 코드 생성
    private String makeVerificationCode(){
        return UUID.randomUUID().toString().substring(0,6).toUpperCase();
    }
}

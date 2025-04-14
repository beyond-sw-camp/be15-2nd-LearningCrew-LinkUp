package com.learningcrew.linkup.linker.command.application.service;

import com.learningcrew.linkup.linker.command.domain.constants.LinkerStatusType;
import com.learningcrew.linkup.linker.command.domain.aggregate.VerificationToken;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import com.learningcrew.linkup.linker.command.domain.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class VerificationCleanupScheduler {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    // 하루 3번: 02:00, 10:00, 18:00 실행
    @Scheduled(cron = "0 23 6,15,23 * * *")
    @Transactional
    public void cleanExpiredPendingUsers() {
        List<VerificationToken> expiredTokens = tokenRepository.findAllByExpiryDateBefore(LocalDateTime.now());

        for (VerificationToken token : expiredTokens) {
            userRepository.findById(token.getUserId()).ifPresent(user -> {
                if (user.getStatus().getStatusType().equals(LinkerStatusType.PENDING.name())) {
                    log.info("만료된 미인증 유저 삭제: {}", user.getEmail());
                    userRepository.delete(user); // CASCADE 걸려있다면 token도 자동 삭제
                }
            });
        }
    }
}
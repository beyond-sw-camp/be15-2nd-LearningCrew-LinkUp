package com.learningcrew.linkup.linker.command.application.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.security.CustomJwtException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.command.application.dto.request.LoginRequest;
import com.learningcrew.linkup.linker.command.application.dto.response.TokenResponse;
import com.learningcrew.linkup.linker.command.domain.aggregate.RefreshToken;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.aggregate.VerificationToken;
import com.learningcrew.linkup.linker.command.domain.constants.LinkerStatusType;
import com.learningcrew.linkup.linker.command.domain.repository.RefreshtokenRepository;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import com.learningcrew.linkup.linker.command.domain.repository.VerificationTokenRepository;
import com.learningcrew.linkup.linker.command.domain.service.TokenDomainService;
import com.learningcrew.linkup.linker.command.domain.service.TokenDomainServiceImpl;
import com.learningcrew.linkup.linker.command.domain.service.UserDomainServiceImpl;
import com.learningcrew.linkup.linker.command.domain.service.UserValidatorServiceImpl;
import com.learningcrew.linkup.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshtokenRepository refreshtokenRepository;
    private final UserValidatorServiceImpl userValidatorService;
    private final TokenDomainService tokenDomainService;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserDomainServiceImpl userDomainService;

    /* 이메일 로직 구현 */
    @Transactional
    public TokenResponse login(LoginRequest request) {

        // 이메일로 회원 조회
        User user = userValidatorService.validateEmail(request.getEmail());

        // 활성화 상태 확인
        if(!(user.getStatus().getStatusType().equals(LinkerStatusType.ACCEPTED.name()))){
            throw new BusinessException(ErrorCode.NOT_AUTHORIZED_USER_EMAIL);
        }

        // 비밀번호 확인
        userValidatorService.validatePassword(request.getPassword(), user.getPassword());

        // 토큰 발급
        String accessToken = tokenDomainService.generateToken(user);
        String refreshToken = tokenDomainService.generateRefreshToken(user);

        //refresh token 저장
        tokenDomainService.saveRefreshToken(user.getEmail(), refreshToken);

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /* 토큰 재발급 구현 */
    @Transactional
    public TokenResponse refreshToken(String providedRefreshToken) {
        jwtTokenProvider.validateToken(providedRefreshToken);
        String email = jwtTokenProvider.getEmailFromJWT(providedRefreshToken);

        RefreshToken storedToken = refreshtokenRepository.findById(email).orElseThrow(()->new CustomJwtException(ErrorCode.USER_NOT_FOUND));

        if(!storedToken.getToken().equals(providedRefreshToken)) {
            throw new CustomJwtException(ErrorCode.REFRESH_TOKEN_MISMATCH);
        }

        if(storedToken.getExpiryDate().before(new Date())) {
            throw new CustomJwtException(ErrorCode.EXPIRED_JWT);
        }

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );

        //토큰 발급
        String accessToken = tokenDomainService.generateToken(user);
        String refreshToken = tokenDomainService.generateRefreshToken(user);

        //refresh token 저장
        tokenDomainService.saveRefreshToken(user.getEmail(), refreshToken);

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /* 로그아웃 기능 구현 */
    @Transactional
    public void logout(String refreshToken) {
        jwtTokenProvider.validateToken(refreshToken);
        String email = jwtTokenProvider.getEmailFromJWT(refreshToken);
        refreshtokenRepository.deleteById(email);
    }

    /* 이메일 인증 구현 */
    @Transactional
    public void verifyEmail(String tokenCode) {
        // 토큰 유효성 검사
        VerificationToken verificationToken = verificationTokenRepository.findByCode(tokenCode).orElseThrow(
                () -> new BusinessException(ErrorCode.INVALID_VERIFICATION_TOKEN)
        );

        // 토큰 만료시간 검사
        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.EXPIRE_VERIFICATION_CODE);
        }

        // 토큰 타입 검사
        if (!verificationToken.getTokenType().equalsIgnoreCase("REGISTER")) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN_TYPE);
        }

        // 사용자 활성화 처리
        User user = userRepository.findById(verificationToken.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

//        if (!user.getStatus().getStatusType().equals("PENDING")) {
//            throw new BusinessException(ErrorCode.ALREADY_VERIFIED);
//        }

        userDomainService.activateUser(user);

        /* 토큰 삭제 */
        verificationTokenRepository.delete(verificationToken);
    }
}

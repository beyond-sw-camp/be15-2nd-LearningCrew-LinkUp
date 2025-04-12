package com.learningcrew.linkup.linker.command.domain.service;

import com.learningcrew.linkup.common.domain.Status;
import com.learningcrew.linkup.common.query.mapper.StatusMapper;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.repository.MemberRepository;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidatorServiceImpl {
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatusMapper statusMapper;

    /* 이메일 중복 검사*/
    public void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    /* 전화번호 중복 검사 */
    public void validateDuplicatePhone(String phoneNumber) {
        if (userRepository.existsByContactNumber(phoneNumber)) {
            throw new BusinessException(ErrorCode.DUPLICATE_CONTACT_NUMBER);
        }
    }

    /* 닉네임 중복 검사 */
    public void validateDuplicateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }

    /* 비밀번호 유효성 검사 */
    public void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    /* 이메일 인증 검사 */
    public User validateEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.INVALID_CREDENTIALS)
                );
    }
}


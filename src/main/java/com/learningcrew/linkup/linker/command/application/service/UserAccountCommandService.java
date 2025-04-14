package com.learningcrew.linkup.linker.command.application.service;

import com.learningcrew.linkup.linker.command.application.dto.response.RegisterResponse;
import com.learningcrew.linkup.linker.command.domain.aggregate.Member;
import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.command.domain.repository.UserRepository;
import com.learningcrew.linkup.linker.command.application.dto.request.UserCreateRequest;
import com.learningcrew.linkup.linker.command.domain.service.MemberDomainService;
import com.learningcrew.linkup.linker.command.domain.service.UserDomainService;
import com.learningcrew.linkup.linker.command.domain.service.UserValidatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserAccountCommandService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserValidatorService userValidatorService;
    private final UserDomainService userDomainService;
    private final MemberDomainService memberDomainService;
    private final EmailService emailService;


    /* 유저 생성 - 회원 가입 */
    @Transactional
    public RegisterResponse registerUser(@Valid UserCreateRequest request) {
        User user = modelMapper.map(request, User.class);

        // 중복 체크
        userValidatorService.validateDuplicateEmail(request.getEmail());
        userValidatorService.validateDuplicatePhone(request.getContactNumber());
        userValidatorService.validateDuplicateNickname(request.getNickname());

        // 비밀번호 암호화
        userDomainService.encryptPassword(user);

        // 권한 부여
        userDomainService.assignRole(user, "USER");

        //상태 부여
        userDomainService.assignStatus(user, "PENDING");

        userDomainService.saveUser(user);

        // User 엔티티 생성 및 저장
        userRepository.save(user);

        Member member = modelMapper.map(request, Member.class);

        // 기본 프로필 이미지 저장
        memberDomainService.setDefaultProfileImage(member);

        // 닉네임 자동 생성
        memberDomainService.assignNicknameIfInvalid(member);

        // Member 엔티티 생성 및 저장
        memberDomainService.saveMember(member, user.getUserId());

        // 이메일 전송 및 저장
        emailService.sendVerificationCode(user.getUserId(), user.getEmail(), user.getUserName());

        return RegisterResponse
                .builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .status("PENDING")
                .build();
    }

    /* 이메일 인증 */

    /* 회원 정보 수정 - 닉네임, 휴대폰, 비밀번호 */

    /* 회원 탈퇴 */

    /* 이메일 찾기 */

    /* 이메일 마스킹처리 */

    /* 비밀번호 재설정 토큰 전송 */

    /* 토큰을 이용한 비밀번호 재설정 */





}

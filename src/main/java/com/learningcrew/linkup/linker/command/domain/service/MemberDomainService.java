package com.learningcrew.linkup.linker.command.domain.service;

import com.learningcrew.linkup.common.util.DefaultImageProperties;
import com.learningcrew.linkup.linker.command.domain.aggregate.Member;
import com.learningcrew.linkup.linker.command.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MemberDomainService {
    private final MemberRepository memberRepository;
    private final DefaultImageProperties defaultImageProperties;

    private static final String NICKNAME_PREFIX = "linker";
    private static final int NICKNAME_SUFFIX_LENGTH = 4;

    /* 기본 프로필 이미지를 설정 */
    public void setDefaultProfileImage(Member member) {
        member.setProfileImageUrl(defaultImageProperties.getDefaultProfileImage());
    }

    /* 닉네임이 없거나 중복된 경우 랜덤 닉네임 생성 */
    public void assignNicknameIfInvalid(Member member) {
        if (!StringUtils.hasText(member.getNickname()) || memberRepository.existsByNickname(member.getNickname())) {
            member.setNickName(generateRandomNickname());
        }
    }

    /* 닉네임 임의자동생성 */
    public String generateRandomNickname() {
        String nickname;
        do {
            String randomSuffix = RandomStringUtils.randomNumeric(NICKNAME_SUFFIX_LENGTH); // ex: 2341
            nickname = NICKNAME_PREFIX + randomSuffix; // ex: linker2341
        } while (memberRepository.existsByNickname(nickname));

        return nickname;
    }

    /* Member 삽입 */
    public void saveMember(Member member, int userId) {
        member.setMemberId(userId);
        memberRepository.save(member);
    }





}

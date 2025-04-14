package com.learningcrew.linkup.linker.query.dto.response;

import com.learningcrew.linkup.linker.query.dto.query.MemberProfileDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "회원 프로필 조회 응답 DTO")
public class UserProfileResponse {
    private MemberProfileDTO member;
}

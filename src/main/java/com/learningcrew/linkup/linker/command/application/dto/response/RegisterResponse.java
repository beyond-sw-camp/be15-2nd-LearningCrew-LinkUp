package com.learningcrew.linkup.linker.command.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@Schema(description = "회원가입 요청 응답 DTO")
@RequiredArgsConstructor
public class RegisterResponse {
    @Schema(description = "회원가입 요청한 회원의 userID")
    int userId;
    @Schema(description = "회원가입 요청한 email")
    String email;
    @Schema(description = "회원가입 진행 상태")
    String status;
}

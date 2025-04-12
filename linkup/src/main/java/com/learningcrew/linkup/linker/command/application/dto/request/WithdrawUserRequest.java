package com.learningcrew.linkup.linker.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "회원 탈퇴 요청 DTO")
public class WithdrawUserRequest {
    private String password;
}

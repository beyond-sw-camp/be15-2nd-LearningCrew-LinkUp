package com.learningcrew.linkup.linker.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "AccessToken 재발급 요청 DTO")
public class RefreshTokenRequest {
    @Schema(description = "refreshToken")
    @NotBlank(message = "refreshToken을 포함해주세요")
    private final String refreshToken;
}

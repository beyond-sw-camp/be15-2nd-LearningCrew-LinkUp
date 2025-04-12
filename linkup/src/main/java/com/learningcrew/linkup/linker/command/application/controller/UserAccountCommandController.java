package com.learningcrew.linkup.linker.command.application.controller;

import com.learningcrew.linkup.linker.command.application.dto.request.FindPasswordRequest;
import com.learningcrew.linkup.linker.command.application.dto.request.LoginRequest;
import com.learningcrew.linkup.linker.command.application.dto.request.UserCreateRequest;
import com.learningcrew.linkup.linker.command.application.dto.request.WithdrawUserRequest;
import com.learningcrew.linkup.linker.command.application.dto.response.RegisterResponse;
import com.learningcrew.linkup.linker.command.application.service.AccountCommandService;
import com.learningcrew.linkup.linker.command.application.service.AccountCommandServiceImpl;
import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.security.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "User API", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserAccountCommandController {
    private final AccountCommandService userCommandService;

    /* 회원 가입 */
    @PostMapping("/register")
    @Operation(
            summary = "회원가입", description = "이메일과 비밀번호, 전화번호 등의 정보를 입력하여 회원으로 가입할 수 있다."
    )
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody UserCreateRequest request) {
        log.info("회원가입 요청: email={}, nickname={}, contact={}",
                request.getEmail(), request.getNickname(), request.getContactNumber());

        RegisterResponse response = userCommandService.registerUser(request);

        log.info("회원가입 완료: userId={}, email={}", response.getUserId(), response.getEmail());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "회원 가입 요청에 성공했습니다. 이메일 인증을 완료해주세요."));
    }

    /* 회원 탈퇴 */
    @DeleteMapping("/withdraw")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody WithdrawUserRequest request) {
        log.info("회원탈퇴 요청: password={}",
                request.getPassword());
        userCommandService.withdrawUser(request.getPassword(), userDetails.getUserId());
        log.info("회원탈퇴 요청 성공");
        return ResponseEntity.ok(ApiResponse.success(null,"회원이 탈퇴되었습니다."));
    }

    /* 계정 복구 */
    @PostMapping("/recover")
    public ResponseEntity<ApiResponse<Void>> recoverUser(){
        return null;
    }

    /* 비밀번호 찾기 - 비밀번호 재설정 url 발송 */
    @PostMapping("/send-pw-reset-url")
    @Operation(summary = "비밀번호 재설정 URL 발송", description = "이메일과 휴대폰로 비밀번호 재설정 url을 이메일로 발송")
    public ResponseEntity<ApiResponse<Void>> sendPasswordResetUrl(@Valid @RequestBody FindPasswordRequest request) {
        return null;
    }

    /* 비밀번호 재설정 */
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@Valid @RequestBody LoginRequest request) {
        log.info("비밀번호 재설정 URL 요청: email={}, contact={}", request.getEmail());
        return null;
    }


}

package com.learningcrew.linkup.linker.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.linker.query.dto.response.UserProfileResponse;
import com.learningcrew.linkup.linker.query.service.UserQueryServiceImpl;
import com.learningcrew.linkup.security.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAuthQueryController {
    private final UserQueryServiceImpl userQueryService;

    /* 프로필 조회 */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        log.info("프로필 조회 요청: userId={}",
                userDetails.getUserId());
        UserProfileResponse userProfileResponse = userQueryService.getUserProfile(userDetails.getUserId());
        log.info("프로필 조회 성공");
        return ResponseEntity.ok(ApiResponse.success(userProfileResponse));
    }

}

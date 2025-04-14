package com.learningcrew.linkup.notification.command.application.controller;

import com.learningcrew.linkup.notification.command.infrastructure.NotificationSseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Tag(name = "SSE 연결 API", description = "실시간 알림 수신을 위한 SSE 엔드포인트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sse")
public class NotificationSseController {

    private final NotificationSseService notificationSseService;

    /**
     * 클라이언트가 SSE 연결을 요청하는 엔드포인트
     * @param userId 연결할 사용자 ID
     * @return SseEmitter (지속 연결)
     */
    @Operation(summary = "SSE 연결", description = "유저 ID로 실시간 알림을 구독합니다.")
    @GetMapping("/connect/{userId}")
    public SseEmitter connect(
            @Parameter(description = "SSE 연결할 유저 ID") @PathVariable Integer userId) {
        return notificationSseService.connect(userId);
    }
}

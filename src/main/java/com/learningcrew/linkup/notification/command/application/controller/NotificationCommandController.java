package com.learningcrew.linkup.notification.command.application.controller;

import com.learningcrew.linkup.notification.command.application.dto.CreateNotificationResponse;
import com.learningcrew.linkup.notification.command.application.dto.EventNotificationRequest;
import com.learningcrew.linkup.notification.command.application.dto.MarkNotificationReadResponse;
import com.learningcrew.linkup.notification.command.application.dto.NotificationSettingRequest;
import com.learningcrew.linkup.notification.command.application.service.NotificationCommandService;
import com.learningcrew.linkup.notification.command.infrastructure.NotificationSseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Tag(name = "Notification Command API", description = "알림 생성 및 설정 관련 API")
@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationCommandController {

    private final NotificationCommandService notificationCommandService;
    private final NotificationSseService notificationSseService;

    /**
     * 알림 전송 설정(동의/차단) 업데이트 API.
     * 경로 변수로 전달된 userId에 대해 사용자의 알림 수신 설정을 업데이트합니다.
     */
    @Operation(summary = "알림 수신 설정 변경", description = "사용자의 알림 수신 여부를 설정합니다.")
    @PostMapping("/{userId}/setting")
    public ResponseEntity<String> updateNotificationSetting(
            @Parameter(description = "유저 ID", required = true) @PathVariable Integer userId,
            @RequestBody NotificationSettingRequest request) {
        notificationCommandService.updateNotificationSetting(userId, request);
        return ResponseEntity.ok("Notification setting updated successfully.");
    }

    /**
     * 이벤트 기반 알림 전송 API.
     * 요청 본문에 포함된 이벤트 정보를 바탕으로 알림을 생성하고 전파합니다.
     */
    @Operation(summary = "알림 전송", description = "이벤트 요청을 기반으로 알림을 생성하고 전송합니다.")
    @PostMapping("/event")
    public ResponseEntity<CreateNotificationResponse> sendEventNotification(
            @RequestBody EventNotificationRequest request) {
        CreateNotificationResponse response = notificationCommandService.sendEventNotification(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 알림 읽음 처리 API.
     * 전달받은 notificationId의 알림을 읽음 상태로 업데이트합니다.
     */
    @Operation(summary = "알림 읽음 처리", description = "알림 ID를 기준으로 해당 알림을 읽음 상태로 변경합니다.")
    @PutMapping("/{notificationId}")
    public ResponseEntity<MarkNotificationReadResponse> markNotificationAsRead(
            @Parameter(description = "알림 ID", required = true) @PathVariable Integer notificationId) {
        MarkNotificationReadResponse response = notificationCommandService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok(response);
    }

    /**
     * SSE 구독 API.
     * 클라이언트가 이 엔드포인트에 접속하여 실시간 알림을 수신합니다.
     */
    @Operation(summary = "SSE 실시간 알림 구독", description = "클라이언트에서 연결하여 실시간 알림을 수신합니다.")
    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter();
        notificationSseService.registerEmitter(emitter);
        return emitter;
    }
}
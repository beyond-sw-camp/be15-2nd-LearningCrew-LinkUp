package com.learningcrew.linkup.meeting.command.application.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingCreateRequest;
import com.learningcrew.linkup.meeting.command.application.dto.request.MeetingParticipationCreateRequest;
import com.learningcrew.linkup.meeting.command.application.dto.response.LeaderUpdateResponse;
import com.learningcrew.linkup.meeting.command.application.dto.response.ManageParticipationResponse;
import com.learningcrew.linkup.meeting.command.application.dto.response.MeetingCommandResponse;
import com.learningcrew.linkup.meeting.command.application.service.MeetingCommandService;
import com.learningcrew.linkup.meeting.command.application.service.MeetingParticipationCommandService;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingParticipationDTO;
import com.learningcrew.linkup.meeting.query.service.MeetingParticipationQueryService;
import com.learningcrew.linkup.meeting.query.service.MeetingQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeetingCommandController {

    private final MeetingCommandService meetingCommandService;
    private final MeetingParticipationCommandService service;
    private final MeetingParticipationQueryService participationQueryService;
    private final MeetingQueryService meetingQueryService;

    @Operation(
            summary = "모임 생성",
            description = "회원이 운동 종목, 날짜(최대 2주 이내), 시간(30분 단위), 장소, 최소/최대 인원을 입력하여 모임을 개설한다."
    )
    @PostMapping("/api/v1/meetings")
    public ResponseEntity<ApiResponse<MeetingCommandResponse>> createMeeting(
            @RequestBody @Validated MeetingCreateRequest meetingCreateRequest
    ) {
        int meetingId = meetingCommandService.createMeeting(meetingCreateRequest);
        MeetingCommandResponse response = new MeetingCommandResponse(meetingId);

        MeetingParticipationCreateRequest request = new MeetingParticipationCreateRequest(meetingCreateRequest.getLeaderId(), meetingId, 2, LocalDateTime.now());
        service.createMeetingParticipation(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @Operation(
            summary = "참가 승인",
            description = "개설자가 모임 신청자 목록을 확인하여 참가 신청을 승인한다."
    )
    @PutMapping("/api/v1/meetings/{meetingId}/participation/{memberId}/accept")
    public ResponseEntity<ApiResponse<ManageParticipationResponse>> acceptParticipation(
            @PathVariable int meetingId, @PathVariable int memberId, @RequestParam int requestedMemberId
    ) {
        int leaderId = meetingQueryService.getMeeting(meetingId)
                .getMeeting()
                .getLeaderId();
        if (leaderId != requestedMemberId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<MeetingParticipationDTO> appliers = participationQueryService.getHistories(meetingId, 1).getMeetingParticipations();

        if (appliers.stream().noneMatch(applier -> applier.getMemberId() == memberId)) {
            return ResponseEntity.badRequest().body(ApiResponse.failure( "참가 신청하지 않은 회원입니다."));
        }

        long participationId = service.acceptParticipation(meetingId, memberId);

        ManageParticipationResponse response
                = ManageParticipationResponse.builder()
                .participationId(participationId)
                .statusId(2)
                .build();

        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    @Operation(
            summary = "참가 거절",
            description = "개설자가 모임 신청자 목록을 확인하여 참가 신청을 거절한다."
    )
    @PutMapping("/api/v1/meetings/{meetingId}/participation/{memberId}/reject")
    public ResponseEntity<ApiResponse<ManageParticipationResponse>> rejectParticipation(
            @PathVariable int meetingId, @PathVariable int memberId, @RequestParam int requestedMemberId
    ) {
        int leaderId = meetingQueryService.getMeeting(meetingId).getMeeting().getLeaderId();
        if (leaderId != requestedMemberId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<MeetingParticipationDTO> appliers = participationQueryService.getHistories(meetingId, 1).getMeetingParticipations();

        if (appliers.stream().noneMatch(applier -> applier.getMemberId() == memberId)) {
            return ResponseEntity.badRequest().body(ApiResponse.failure("참가 신청하지 않은 회원입니다."));
        }

        long participationId = service.rejectParticipation(meetingId, memberId);

        ManageParticipationResponse response
                = ManageParticipationResponse.builder()
                .participationId(participationId)
                .statusId(3)
                .build();

        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    @Operation(
            summary = "개설자 참가 취소",
            description = "개설자가 모임 신청자 목록을 확인하여 참가 신청을 거절한다."
    )
    @PutMapping("/api/v1/meetings/{meetingId}/change-leader/{memberId}")
    public ResponseEntity<ApiResponse<LeaderUpdateResponse>> updateLeader(
            @PathVariable int meetingId, @PathVariable int memberId, @RequestParam int requestedMemberId
    ) {
        meetingCommandService.updateLeader(meetingId, memberId, requestedMemberId);
        LeaderUpdateResponse response = new LeaderUpdateResponse(meetingId);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "모집 취소",
            description = "개설자가 인원 모집을 취소한다."
    )
    @DeleteMapping("/api/v1/meetings/{meetingId}/cancel")
    public ResponseEntity<ApiResponse<MeetingCommandResponse>> deleteMeeting(
            @PathVariable int meetingId, @RequestParam int memberId
    ) {
        int leaderId = meetingQueryService.getMeeting(meetingId)
                .getMeeting()
                .getLeaderId();

        if (leaderId != memberId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        MeetingCommandResponse response = new MeetingCommandResponse(meetingId);

        meetingCommandService.deleteMeeting(meetingId);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }



}
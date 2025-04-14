package com.learningcrew.linkup.meeting.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.meeting.query.dto.request.MeetingSearchRequest;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingDetailResponse;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingListResponse;
import com.learningcrew.linkup.meeting.query.service.MeetingQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingQueryController {

    private final MeetingQueryService meetingQueryService;

    @Operation(
            summary = "모임 상세 조회",
            description = "회원이 개설된 모임 목록에서 특정 모임의 상세 정보를 조회한다"
    )
    @GetMapping("/api/v1/meetings/{meetingId}")
    public ResponseEntity<ApiResponse<MeetingDetailResponse>> getMeeting(@PathVariable int meetingId) {
        MeetingDetailResponse response = meetingQueryService.getMeeting(meetingId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(
            summary = "모임 조회 (목록별, 조건별 조회)",
            description = "회원이 검색 조건을 설정하여 모임 목록을 조회한다."
    )
    @GetMapping("/api/v1/meetings")
    public ResponseEntity<ApiResponse<MeetingListResponse>> getMeetings(
            MeetingSearchRequest meetingSearchRequest
    ) {
        MeetingListResponse response = meetingQueryService.getMeetings(meetingSearchRequest);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}

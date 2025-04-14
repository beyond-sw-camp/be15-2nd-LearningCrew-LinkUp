package com.learningcrew.linkup.meeting.query.controller;

import com.learningcrew.linkup.common.dto.ApiResponse;
import com.learningcrew.linkup.meeting.query.dto.request.InterestedMeetingSearchRequest;
import com.learningcrew.linkup.meeting.query.dto.response.InterestedMeetingListResponse;
import com.learningcrew.linkup.meeting.query.service.InterestedMeetingQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestedMeetingQueryController {

    private final InterestedMeetingQueryService service;

    @Operation(
            summary = "모임 찜 목록 조회",
            description = "회원이 자신의 모임 찜 목록을 조회한다."
    )
    @GetMapping("/api/v1/meetings/{meetingId}/interested")
    public ResponseEntity<ApiResponse<InterestedMeetingListResponse>> getInterestedMeetings(
            InterestedMeetingSearchRequest request
    ) {
        InterestedMeetingListResponse response = service.getInterestedMeetings(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}

package com.learningcrew.linkup.meeting.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "모임 찜 등록 요청")
public class InterestedMeetingCommandRequest {
    @Min(value = 1)
    @Schema(description = "모임 ID")
    private int meetingId;
    @Min(value = 1)
    @Schema(description = "회원 ID")
    private int memberId;
}

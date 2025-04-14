package com.learningcrew.linkup.meeting.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeetingDetailResponse {
    private final MeetingDTO meeting;
}

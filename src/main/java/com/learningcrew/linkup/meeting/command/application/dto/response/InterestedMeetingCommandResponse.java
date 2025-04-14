package com.learningcrew.linkup.meeting.command.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InterestedMeetingCommandResponse {
    private int meetingId;
    private int memberId;
}

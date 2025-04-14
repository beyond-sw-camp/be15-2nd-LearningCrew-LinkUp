package com.learningcrew.linkup.meeting.query.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BestPlayerDTO {

    private int meetingId;
    private int memberId;
}
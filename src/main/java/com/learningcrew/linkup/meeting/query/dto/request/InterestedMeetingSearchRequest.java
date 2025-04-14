package com.learningcrew.linkup.meeting.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestedMeetingSearchRequest {
    private Integer page = 1; // 값이 없을 때의 default
    private Integer size = 10;

    private Integer memberId;
    private Integer meetingId;

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }

}

package com.learningcrew.linkup.meeting.query.dto.response;

import com.learningcrew.linkup.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MeetingListResponse {

    private final List<MeetingDTO> meetings;
    private final Pagination pagination;
}

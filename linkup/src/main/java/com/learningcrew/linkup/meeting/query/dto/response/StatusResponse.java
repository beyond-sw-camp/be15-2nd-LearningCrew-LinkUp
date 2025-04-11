package com.learningcrew.linkup.meeting.query.dto.response;

import com.learningcrew.linkup.common.domain.Status;
import com.learningcrew.linkup.common.query.mapper.StatusMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResponse {
    private Status status;

    public int getStatusId() {
        return status.getStatusId();
    }

}

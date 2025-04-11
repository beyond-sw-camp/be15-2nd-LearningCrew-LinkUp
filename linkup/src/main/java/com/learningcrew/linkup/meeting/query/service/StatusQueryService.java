package com.learningcrew.linkup.meeting.query.service;

import com.learningcrew.linkup.common.query.mapper.StatusMapper;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusQueryService {

    private final StatusMapper mapper;

    public int getStatusId(String statusType) {
        return mapper.statusByStatusType(statusType)
                .orElseThrow(() -> new BusinessException(ErrorCode.BAD_REQUEST)).getStatusId();
    }

}

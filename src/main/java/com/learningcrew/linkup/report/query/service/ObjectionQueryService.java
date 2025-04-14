package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.report.query.dto.request.ObjectionSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.ObjectionListResponse;

public interface ObjectionQueryService {

    // 이의 제기 목록 조회 (전체, 상태별, 사용자별)
    ObjectionListResponse getObjections(ObjectionSearchRequest request);
}

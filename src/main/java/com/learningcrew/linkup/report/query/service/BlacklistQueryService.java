package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.report.query.dto.request.BlacklistSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.BlacklistListResponse;

public interface BlacklistQueryService {

    // 전체 블랙리스트 목록 조회 (페이징)
    BlacklistListResponse getBlacklist(BlacklistSearchRequest request);
}

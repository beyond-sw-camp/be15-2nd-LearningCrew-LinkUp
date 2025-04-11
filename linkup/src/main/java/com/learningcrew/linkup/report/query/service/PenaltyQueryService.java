package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.report.query.dto.request.PenaltySearchRequest;
import com.learningcrew.linkup.report.query.dto.response.PenaltyListResponse;

public interface PenaltyQueryService {

    // 제재 내역 조회 (전체, 유형별, 사용자별 통합)
    PenaltyListResponse getPenalties(PenaltySearchRequest request);

    PenaltyListResponse getPenaltiesByMemberAndType(Long memberId, String penaltyType, PenaltySearchRequest request);

}

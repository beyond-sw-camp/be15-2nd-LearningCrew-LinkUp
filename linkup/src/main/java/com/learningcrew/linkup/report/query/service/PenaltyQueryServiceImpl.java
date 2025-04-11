package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.report.query.dto.request.PenaltySearchRequest;
import com.learningcrew.linkup.report.query.dto.response.PenaltyDTO;
import com.learningcrew.linkup.report.query.dto.response.PenaltyListResponse;
import com.learningcrew.linkup.report.query.mapper.PenaltyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PenaltyQueryServiceImpl implements PenaltyQueryService {

    private final PenaltyMapper penaltyMapper;

    @Override
    public PenaltyListResponse getPenalties(PenaltySearchRequest request) {
        List<PenaltyDTO> penalties = penaltyMapper.selectPenalties(request);
        long totalItems = penaltyMapper.countPenalties(request);

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalItems(totalItems)
                .totalPage((int) Math.ceil((double) totalItems / request.getSize()))
                .build();

        return PenaltyListResponse.builder()
                .penalties(penalties)
                .pagination(pagination)
                .build();
    }

    @Override
    public PenaltyListResponse getPenaltiesByMemberAndType(Long memberId, String penaltyType, PenaltySearchRequest request) {
        int limit = request.getSize();
        int offset = (request.getPage() - 1) * limit;

        List<PenaltyDTO> penalties = penaltyMapper.selectPenaltiesByMemberAndType(memberId, penaltyType, limit, offset);
        long totalItems = penaltyMapper.countPenaltiesByMemberAndType(memberId, penaltyType);

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalItems(totalItems)
                .totalPage((int) Math.ceil((double) totalItems / limit))
                .build();

        return PenaltyListResponse.builder()
                .penalties(penalties)
                .pagination(pagination)
                .build();
    }

}

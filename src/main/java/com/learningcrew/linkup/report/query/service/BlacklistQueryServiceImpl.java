package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.report.query.dto.request.BlacklistSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.BlacklistDTO;
import com.learningcrew.linkup.report.query.dto.response.BlacklistListResponse;
import com.learningcrew.linkup.report.query.mapper.BlacklistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlacklistQueryServiceImpl implements BlacklistQueryService {

    private final BlacklistMapper blacklistMapper;

    @Override
    public BlacklistListResponse getBlacklist(BlacklistSearchRequest request) {
        List<BlacklistDTO> list = blacklistMapper.selectBlacklist(request);
        long totalItems = blacklistMapper.countBlacklist(request);

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalItems(totalItems)
                .totalPage((int) Math.ceil((double) totalItems / request.getSize()))
                .build();

        return BlacklistListResponse.builder()
                .blacklist(list)
                .pagination(pagination)
                .build();
    }
}

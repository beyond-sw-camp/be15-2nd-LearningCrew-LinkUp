package com.learningcrew.linkup.report.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.report.query.dto.request.ObjectionSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.ObjectionDTO;
import com.learningcrew.linkup.report.query.dto.response.ObjectionListResponse;
import com.learningcrew.linkup.report.query.mapper.ObjectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ObjectionQueryServiceImpl implements ObjectionQueryService {

    private final ObjectionMapper objectionMapper;

    @Override
    public ObjectionListResponse getObjections(ObjectionSearchRequest request) {
        List<ObjectionDTO> objections = objectionMapper.selectObjections(request);
        long totalItems = objectionMapper.countObjections(request);

        Pagination pagination = Pagination.builder()
                .currentPage(request.getPage())
                .totalItems(totalItems)
                .totalPage((int) Math.ceil((double) totalItems / request.getSize()))
                .build();

        return ObjectionListResponse.builder()
                .objections(objections)
                .pagination(pagination)
                .build();
    }
}

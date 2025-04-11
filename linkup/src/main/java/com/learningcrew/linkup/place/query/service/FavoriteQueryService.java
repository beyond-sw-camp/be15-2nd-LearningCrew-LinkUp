package com.learningcrew.linkup.place.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.place.query.dto.request.FavoriteRequest;
import com.learningcrew.linkup.place.query.dto.response.FavoriteListResponse;
import com.learningcrew.linkup.place.query.dto.response.FavoriteDto;
import com.learningcrew.linkup.place.query.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteQueryService {

    private final FavoriteMapper favoriteMapper;

    @Transactional(readOnly = true)
    public FavoriteListResponse getFavoriteList(int memberId, FavoriteRequest favoriteRequest) {
        // memberId를 요청 조건에 세팅
        favoriteRequest.setMemberId(memberId);

        List<FavoriteDto> favorites = favoriteMapper.selectFavorites(favoriteRequest);
        long totalItems = favoriteMapper.countFavorites(favoriteRequest);
        int page = favoriteRequest.getPage();
        int size = favoriteRequest.getSize();
        int totalPage = (int) Math.ceil((double) totalItems / size);

        return FavoriteListResponse.builder()
                .favorites(favorites)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage(totalPage)
                        .totalItems(totalItems)
                        .build())
                .build();
    }
}

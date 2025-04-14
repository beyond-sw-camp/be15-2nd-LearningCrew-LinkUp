package com.learningcrew.linkup.place.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.place.query.dto.request.PlaceListRequest;
import com.learningcrew.linkup.place.query.dto.response.*;
import com.learningcrew.linkup.place.query.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceQueryService {
    private final PlaceMapper placeMapper;

    @Transactional(readOnly = true)
    public PlaceListResponse getPlaces(PlaceListRequest placeListRequest) {

        List<PlaceDto> places = placeMapper.selectAllPlaces(placeListRequest);
        long totalItems = placeMapper.countPlaces(placeListRequest);
        int page = placeListRequest.getPage();
        int size = placeListRequest.getSize();
        int totalPage = (int) Math.ceil((double) totalItems / size);

        return PlaceListResponse.builder()
                .place(places)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage(totalPage)
                        .totalItems(totalItems)
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    public PlaceListResponse getPlacesByAdmin(PlaceListRequest placeListRequest) {

        List<PlaceDto> places = placeMapper.selectAllPlacesByAdmin(placeListRequest);
        long totalItems = placeMapper.countPlacesByAdmin(placeListRequest);
        int page = placeListRequest.getPage();
        int size = placeListRequest.getSize();
        int totalPage = (int) Math.ceil((double) totalItems / size);

        return PlaceListResponse.builder()
                .place(places)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage(totalPage)
                        .totalItems(totalItems)
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    public PlaceDetailResponse getPlaceDetails(int placeId){
        return placeMapper.selectPlaceDetail(placeId);
    }
}

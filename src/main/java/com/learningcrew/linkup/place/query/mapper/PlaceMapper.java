package com.learningcrew.linkup.place.query.mapper;

import com.learningcrew.linkup.place.query.dto.request.FavoriteRequest;
import com.learningcrew.linkup.place.query.dto.request.PlaceListRequest;
import com.learningcrew.linkup.place.query.dto.response.FavoriteDto;
import com.learningcrew.linkup.place.query.dto.response.PlaceDetailResponse;
import com.learningcrew.linkup.place.query.dto.response.PlaceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaceMapper {

    // 회원의 즐겨찾기 목록 조회 (페이징 적용)
    List<PlaceDto> selectAllPlaces(PlaceListRequest placeListRequest);

    List<PlaceDto> selectAllPlacesByAdmin(PlaceListRequest placeListRequest);

    PlaceDetailResponse selectPlaceDetail(@Param("placeId")int placeId);

    long countPlaces(PlaceListRequest placeListRequest);

    long countPlacesByAdmin(PlaceListRequest placeListRequest);

}

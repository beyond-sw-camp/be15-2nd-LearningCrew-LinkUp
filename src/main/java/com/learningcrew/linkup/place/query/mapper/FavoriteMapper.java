package com.learningcrew.linkup.place.query.mapper;

import com.learningcrew.linkup.place.query.dto.request.FavoriteRequest;
import com.learningcrew.linkup.place.query.dto.response.FavoriteDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    // 회원의 즐겨찾기 목록 조회 (페이징 적용)
    List<FavoriteDto> selectFavorites(@Param("favoriteRequest") FavoriteRequest favoriteRequest);

    // 회원의 즐겨찾기 총 개수 조회
    long countFavorites(@Param("favoriteRequest") FavoriteRequest favoriteRequest);
}

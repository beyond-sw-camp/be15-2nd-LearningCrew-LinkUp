package com.learningcrew.linkup.report.query.mapper;

import com.learningcrew.linkup.report.query.dto.request.BlacklistSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.BlacklistDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlacklistMapper {

    // 블랙리스트 전체 조회 (페이징)
    List<BlacklistDTO> selectBlacklist(@Param("req") BlacklistSearchRequest request);
    long countBlacklist(@Param("req") BlacklistSearchRequest request);
}

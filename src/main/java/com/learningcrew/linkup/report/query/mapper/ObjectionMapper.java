package com.learningcrew.linkup.report.query.mapper;

import com.learningcrew.linkup.report.query.dto.request.ObjectionSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.ObjectionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ObjectionMapper {

    // 이의 제기 목록 조회 (전체, 상태별, 사용자별 통합)
    List<ObjectionDTO> selectObjections(@Param("req") ObjectionSearchRequest request);

    // 이의 제기 전체 건수 (페이징용)
    long countObjections(@Param("req") ObjectionSearchRequest request);
}

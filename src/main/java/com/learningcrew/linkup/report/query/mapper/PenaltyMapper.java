package com.learningcrew.linkup.report.query.mapper;

import com.learningcrew.linkup.report.query.dto.request.PenaltySearchRequest;
import com.learningcrew.linkup.report.query.dto.response.PenaltyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PenaltyMapper {

    // 사용자 제재 내역 목록 조회 (조건: penaltyType, memberId 포함)
    List<PenaltyDTO> selectPenalties(@Param("req") PenaltySearchRequest request);

    // 사용자 제재 전체 건수 (페이징용)
    long countPenalties(@Param("req") PenaltySearchRequest request);

    List<PenaltyDTO> selectPenaltiesByMemberAndType(@Param("memberId") Long memberId,
                                                    @Param("penaltyType") String penaltyType,
                                                    @Param("limit") int limit,
                                                    @Param("offset") int offset);

    long countPenaltiesByMemberAndType(@Param("memberId") Long memberId,
                                       @Param("penaltyType") String penaltyType);


}

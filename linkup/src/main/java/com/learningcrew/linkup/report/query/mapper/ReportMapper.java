package com.learningcrew.linkup.report.query.mapper;

import com.learningcrew.linkup.report.query.dto.request.ReportSearchRequest;
import com.learningcrew.linkup.report.query.dto.request.ReporteeSearchRequest;
import com.learningcrew.linkup.report.query.dto.request.ReporterSearchRequest;
import com.learningcrew.linkup.report.query.dto.response.ReportDTO;
import com.learningcrew.linkup.report.query.dto.response.ReportUserCountDTO;
import com.learningcrew.linkup.report.query.dto.response.ReportUserScoreDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {

    // 전체 신고 내역 조회 (신고자, 피신고자, 유형, 상태로 필터 가능)
    List<ReportDTO> selectReports(@Param("req") ReportSearchRequest request);
    long countReports(@Param("req") ReportSearchRequest request);

    // 누적 신고 횟수 기준 신고자 목록 조회
    List<ReportUserCountDTO> selectReporterListByCount(@Param("req") ReporterSearchRequest request);
    long countReporterListByCount();

    // 누적 신고 점수 기준 피신고자 목록 조회
    List<ReportUserScoreDTO> selectReporteeListByScore(@Param("req") ReporteeSearchRequest request);
    long countReporteeListByScore();

}

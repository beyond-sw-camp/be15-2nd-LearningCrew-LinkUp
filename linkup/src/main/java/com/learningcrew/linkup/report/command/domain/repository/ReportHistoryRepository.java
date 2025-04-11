package com.learningcrew.linkup.report.command.domain.repository;

import com.learningcrew.linkup.report.command.domain.aggregate.ReportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportHistoryRepository extends JpaRepository<ReportHistory, Integer> {

    // 중복 신고 방지를 위한 조건
    Optional<ReportHistory> findByReporterIdAndTargetIdAndReportTypeIdAndPostIdAndCommentId(
            Integer reporterId,
            Integer targetId,
            Byte reportTypeId,
            Integer postId,
            Long commentId
    );
}

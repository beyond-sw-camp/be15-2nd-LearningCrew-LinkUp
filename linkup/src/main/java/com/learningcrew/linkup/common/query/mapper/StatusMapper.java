package com.learningcrew.linkup.common.query.mapper;

import com.learningcrew.linkup.common.domain.Status;
import com.learningcrew.linkup.common.dto.query.StatusDTO;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface StatusMapper {
    // status_type으로 상태 조회
    @Results(id = "statusMap", value = {
            @Result(property = "statusId", column = "status_id"),
            @Result(property = "statusType", column = "status_type")
    })
    @Select("SELECT status_id, status_type from STATUS WHERE status_type = #{statusType}")
    Optional<StatusDTO> statusByStatusType(String statusType);

    // status_id로 상태 조회
    @ResultMap("statusMap")
    @Select("SELECT status_id, status_type FROM STATUS WHERE status_id = #{statusId}")
    Optional<StatusDTO> statusByStatusId(int statusId);

    // status_type으로 존재 유무 확인
    @Select("SELECT EXISTS(SELECT 1 FROM STATUS WHERE status_type = #{statusType})")
    boolean existsByStatusType(String statusType);
}

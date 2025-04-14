package com.learningcrew.linkup.common.query.mapper;

import com.learningcrew.linkup.common.dto.query.StatusDTO;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StatusMapper {
    @Select("SELECT status_id, status_type from STATUS WHERE status_type = #{statusType}")
    @Results(id = "statusMap", value = {
            @Result(property = "statusId", column = "status_id"),
            @Result(property = "statusType", column = "status_type")
    })
    Optional<StatusDTO> statusByStatusType(String statusType);
}

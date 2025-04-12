package com.learningcrew.linkup.common.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;


import com.learningcrew.linkup.common.dto.query.SportTypeDTO;
import org.apache.ibatis.annotations.*;


@Mapper
public interface SportTypeMapper {

    /* sport_name으로 sportType 조회 */
    @Results(id = "sportTypeMap", value = {
            @Result(property = "sportTypeId", column = "sport_type_id"),
            @Result(property = "sportName", column = "sport_name")
    })
    @Select("SELECT sport_type_id, sport_name FROM sport_type WHERE sport_name = #{sportName}")
    Optional<SportTypeDTO> findBySportName(String sportName);

    /* sport_id로 sportType 조회 */
    @ResultMap("sportTypeMap")
    @Select("SELECT sport_type_id, sport_name FROM sport_type WHERE sport_type_id = #{id}")
    Optional<SportTypeDTO> findBySportTypeId(int id);

    /* sport_name으로 유무 확인 */
    @Select("SELECT EXISTS(SELECT 1 FROM sport_name WHERE sport_name = #{sportName})")
    boolean existsBySportName(String sportName);
}


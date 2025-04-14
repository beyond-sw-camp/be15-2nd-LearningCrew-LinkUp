package com.learningcrew.linkup.common.query.mapper;

import com.learningcrew.linkup.common.domain.Role;
import com.learningcrew.linkup.common.dto.query.RoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface RoleMapper {
    @Results(id = "roleMap", value = {
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    @Select("SELECT role_id, role_name from ROLE WHERE role_name = #{roleName}")
    Optional<RoleDTO> roleByRoleName(String roleName);
}

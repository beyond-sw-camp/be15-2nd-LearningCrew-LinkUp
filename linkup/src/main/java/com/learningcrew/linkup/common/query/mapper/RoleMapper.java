package com.learningcrew.linkup.common.query.mapper;

import com.learningcrew.linkup.common.domain.Role;
import com.learningcrew.linkup.common.dto.query.RoleDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RoleMapper {

    //role_name으로 단일조회
    @Results(id = "roleMap", value = {
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    @Select("SELECT role_id, role_name from ROLE WHERE role_name = #{roleName}")
    Optional<RoleDTO> roleByRoleName(String roleName);

    // role_id로 단일 조회
    @ResultMap("roleMap")
    @Select("SELECT role_id, role_name FROM ROLE WHERE role_id = #{roleId}")
    Optional<RoleDTO> findByRoleId(int roleId);

    // 전체 역할 목록 조회
    @Select("SELECT role_id, role_name FROM ROLE")
    List<RoleDTO> findAllRoles();

    // role_name으로 유무 확인
    @Select("SELECT EXISTS(SELECT 1 FROM ROLE WHERE role_name = #{ roleName })")
    boolean existsByRoleName(String roleName);

    // role_id로 role_name 조회
    @Select("SELECT role_name FROM ROLE WHERE role_id = #{roleId}")
    Optional<String> getRoleNameById(int roleId);

    // role_name으로 role_id 조회
    @Select("SELECT role_id FROM ROLE WHERE role_name = #{roleName}")
    Optional<Integer> getRoleIdByName(String roleName);
}

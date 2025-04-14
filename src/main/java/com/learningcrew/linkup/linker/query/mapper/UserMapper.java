package com.learningcrew.linkup.linker.query.mapper;

import com.learningcrew.linkup.linker.query.dto.query.UserProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserProfileDTO> findAllUsers(int roleId);
}

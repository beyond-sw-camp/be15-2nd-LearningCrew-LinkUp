package com.learningcrew.linkup.linker.query.mapper;

import com.learningcrew.linkup.linker.command.domain.aggregate.User;
import com.learningcrew.linkup.linker.query.dto.query.UserDeleteDTO;
import com.learningcrew.linkup.linker.query.dto.query.UserProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    List<UserProfileDTO> findAllUsers(int roleId);

    Optional<User> findByUserEmail(String email);

    Optional<UserDeleteDTO> findByUserUserId(int userId);
}

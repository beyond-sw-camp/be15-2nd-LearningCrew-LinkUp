package com.learningcrew.linkup.linker.query.service;

import com.learningcrew.linkup.common.domain.Role;
import com.learningcrew.linkup.common.dto.query.RoleDTO;
import com.learningcrew.linkup.common.query.mapper.RoleMapper;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.query.dto.query.MemberProfileDTO;
import com.learningcrew.linkup.linker.query.dto.query.UserProfileDTO;
import com.learningcrew.linkup.linker.query.dto.response.UserListResponse;
import com.learningcrew.linkup.linker.query.dto.response.UserProfileResponse;
import com.learningcrew.linkup.linker.query.mapper.MemberMapper;
import com.learningcrew.linkup.common.query.mapper.StatusMapper;
import com.learningcrew.linkup.linker.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final MemberMapper memberMapper;
    private final UserMapper userMapper;
    private final StatusMapper statusMapper;
    private final RoleMapper roleMapper;

    public UserProfileResponse getUserProfile(int userId) {
        MemberProfileDTO memberProfileDTO = Optional.ofNullable(memberMapper.getUserProfileByEmail(userId))
                .orElseThrow(() -> new RuntimeException("유저 정보 찾지 못함"));

        return UserProfileResponse
                .builder()
                .member(memberProfileDTO)
                .build();
    }

    public UserListResponse getUserList() {
        int roleId = getRoleIdByRoleName("USER");
        List<UserProfileDTO> userProfileDTOList = userMapper.findAllUsers(roleId);
        return UserListResponse
                .builder()
                .userProfileDTOList(userProfileDTOList)
                .build();
    }

    public int getRoleIdByRoleName(String roleName) {
        RoleDTO role = roleMapper.roleByRoleName(roleName).orElseThrow(
                () -> new BusinessException(ErrorCode.INVALID_ROLE)
        );
        return role.getRoleId();
    }
}

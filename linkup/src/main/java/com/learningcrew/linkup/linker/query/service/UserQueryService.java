package com.learningcrew.linkup.linker.query.service;

import com.learningcrew.linkup.linker.query.dto.response.UserListResponse;
import com.learningcrew.linkup.linker.query.dto.response.UserProfileResponse;

public interface UserQueryService {
    public UserProfileResponse getUserProfile(int userId);
    public UserListResponse getUserList();
    public int getRoleIdByRoleName(String roleName);
}

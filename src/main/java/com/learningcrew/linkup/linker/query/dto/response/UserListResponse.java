package com.learningcrew.linkup.linker.query.dto.response;

import com.learningcrew.linkup.linker.query.dto.query.UserProfileDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserListResponse {
    private List<UserProfileDTO> userProfileDTOList;
}

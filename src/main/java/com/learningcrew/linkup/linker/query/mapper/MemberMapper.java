package com.learningcrew.linkup.linker.query.mapper;

import com.learningcrew.linkup.linker.query.dto.query.MemberProfileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberProfileDTO getUserProfileByEmail(int userId);
}

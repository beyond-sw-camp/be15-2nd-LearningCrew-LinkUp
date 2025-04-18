package com.learningcrew.linkup.linker.query.mapper;

import com.learningcrew.linkup.linker.query.dto.query.FriendInfoDto;
import com.learningcrew.linkup.linker.query.dto.query.FriendRequestStatusDto;
import com.learningcrew.linkup.linker.query.dto.query.UserMeetingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {

    List<FriendInfoDto> findAcceptedFriends(int memberId);

    List<FriendRequestStatusDto> findIncomingFriendRequests(int addresseeId);

    List<UserMeetingDto> findMeetingsCreatedByFriends(int userId);
}


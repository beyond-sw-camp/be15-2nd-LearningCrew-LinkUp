package com.learningcrew.linkup.meeting.query.mapper;

import com.learningcrew.linkup.meeting.query.dto.request.InterestedMeetingSearchRequest;
import com.learningcrew.linkup.meeting.query.dto.response.InterestedMeetingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestedMeetingMapper {
    InterestedMeetingDTO selectInterestedMeeting(int meetingId, int memberId);

    List<InterestedMeetingDTO> selectInterestedMeetings(InterestedMeetingSearchRequest request);

    List<InterestedMeetingDTO> selectAllInterestedMeetingByMeetingId(int meetingId);

    long countInterestedMeetings(InterestedMeetingSearchRequest request);
}

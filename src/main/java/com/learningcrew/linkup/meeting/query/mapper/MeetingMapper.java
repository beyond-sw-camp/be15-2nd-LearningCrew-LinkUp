package com.learningcrew.linkup.meeting.query.mapper;

import com.learningcrew.linkup.meeting.query.dto.request.MeetingSearchRequest;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingMapper {
    /* 모임 ID로 모임 하나 조회 */
    MeetingDTO selectMeetingById(int meetingId);

    /* 검색/페이징 조건을 전달 받아 모임 목록 조회 */
    List<MeetingDTO> selectMeetings(MeetingSearchRequest meetingSearchRequest);

    long countMeetings(MeetingSearchRequest meetingSearchRequest);
}

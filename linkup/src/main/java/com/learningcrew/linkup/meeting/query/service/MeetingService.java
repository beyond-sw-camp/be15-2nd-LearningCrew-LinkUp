package com.learningcrew.linkup.meeting.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingStatus;
import com.learningcrew.linkup.meeting.query.dto.request.MeetingSearchRequest;
import com.learningcrew.linkup.meeting.query.dto.response.*;
import com.learningcrew.linkup.meeting.query.mapper.MeetingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingMapper meetingMapper;

    public MeetingListResponse getMeetings(MeetingSearchRequest request) {
        List<MeetingSummaryDTO> meetings = meetingMapper.selectMeetings(request);
        long total = meetingMapper.countMeetings(request);

        // 상태 enum 매핑
        meetings.forEach(m -> {
            MeetingStatus status = MeetingStatus.fromId(m.getStatusId());
            m.setStatusName(status.getLabel());
        });

        return MeetingListResponse.builder()
                .meetings(meetings)
                .pagination(Pagination.builder()
                        .currentPage(request.getPage())
                        .totalItems(total)
                        .totalPage((int) Math.ceil((double) total / request.getSize()))
                        .build())
                .build();
    }

    public MeetingDetailResponse getMeetingDetail(int meetingId) {
        // 모임 정보 조회
        MeetingDTO meeting = meetingMapper.selectMeetingById(meetingId);
        if (meeting == null) {
            throw new BusinessException(ErrorCode.MEETING_NOT_FOUND);
        }

        // 상태 한글 이름 설정
        MeetingStatus status = MeetingStatus.fromId(meeting.getStatusId());
        meeting.setStatusName(status.getLabel());

        List<MemberDTO> participants = Collections.emptyList();

        // 상태가 ACCEPTED일 때만 참가자 목록 조회
        if (status == MeetingStatus.ACCEPTED) {
            participants = meetingMapper.selectParticipantsByMeetingId(meetingId);
        }

        return MeetingDetailResponse.builder()
                .meeting(meeting)
                .participants(participants)
                .build();
    }

    public MeetingListResponse getAcceptedMeetingsByUser(int userId) {
        List<MeetingSummaryDTO> meetings = meetingMapper.selectAcceptedMeetingsByUserId(userId);

        meetings.forEach(m -> {
            MeetingStatus status = MeetingStatus.fromId(m.getStatusId());
            m.setStatusName(status.getLabel());
        });

        return MeetingListResponse.builder()
                .meetings(meetings)
                .pagination(null) // 단건/전체 반환이므로 페이징 불필요
                .build();
    }

    public MeetingListResponse getPastMeetingsByUser(int userId) {
        List<MeetingSummaryDTO> meetings = meetingMapper.selectPastMeetingsByUserId(userId, LocalDate.now());

        meetings.forEach(m -> {
            MeetingStatus status = MeetingStatus.fromId(m.getStatusId());
            m.setStatusName(status.getLabel());
        });

        return MeetingListResponse.builder()
                .meetings(meetings)
                .pagination(null)
                .build();
    }

    public MeetingListResponse getInterestedMeetings(int userId) {
        List<MeetingSummaryDTO> meetings = meetingMapper.selectInterestedMeetings(userId);

        meetings.forEach(m -> {
            MeetingStatus status = MeetingStatus.fromId(m.getStatusId());
            m.setStatusName(status.getLabel());
        });

        return MeetingListResponse.builder()
                .meetings(meetings)
                .pagination(null)
                .build();
    }



}

package com.learningcrew.linkup.meeting.query.service;

import com.learningcrew.linkup.common.dto.Pagination;
import com.learningcrew.linkup.meeting.query.dto.request.InterestedMeetingSearchRequest;
import com.learningcrew.linkup.meeting.query.dto.response.InterestedMeetingDTO;
import com.learningcrew.linkup.meeting.query.dto.response.InterestedMeetingListResponse;
import com.learningcrew.linkup.meeting.query.mapper.InterestedMeetingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestedMeetingQueryService {

    private final InterestedMeetingMapper mapper;

    /* 회원의 모임 찜 목록 조회 */
    @Transactional(readOnly = true)
    public InterestedMeetingListResponse getInterestedMeetings(InterestedMeetingSearchRequest request) {
        List<InterestedMeetingDTO> interestedMeetings = mapper.selectInterestedMeetings(request);

        long totalItems = mapper.countInterestedMeetings(request);

        int page = request.getPage();
        int size = request.getSize();

        return InterestedMeetingListResponse.builder()
                .interestedMeetings(interestedMeetings)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int) Math.ceil((double) totalItems / size ))
                        .totalItems(totalItems)
                        .build())
                .build();
    }

    /* 모임의 찜 등록 회원 조회 -> 알림 발송용 */

}

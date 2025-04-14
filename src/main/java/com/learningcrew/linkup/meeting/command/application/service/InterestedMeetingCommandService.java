package com.learningcrew.linkup.meeting.command.application.service;

import com.learningcrew.linkup.meeting.command.application.dto.request.InterestedMeetingCommandRequest;
import com.learningcrew.linkup.meeting.command.domain.aggregate.InterestedMeeting;
import com.learningcrew.linkup.meeting.command.domain.aggregate.InterestedMeetingId;
import com.learningcrew.linkup.meeting.command.domain.repository.InterestedMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterestedMeetingCommandService {

    private final InterestedMeetingRepository repository;

    /* 모임 찜 등록 */
    @Transactional
    public int createInterestedMeeting(InterestedMeetingCommandRequest request) {
        InterestedMeetingId id = new InterestedMeetingId(request.getMeetingId(), request.getMemberId());
        InterestedMeeting interestedMeeting = new InterestedMeeting(id);

        repository.save(interestedMeeting);
        return interestedMeeting.getMeetingId();
    }


    /* 모임 찜 취소 */
    @Transactional
    public void deleteInterestedMeeting(InterestedMeetingCommandRequest request) {
        InterestedMeetingId id = new InterestedMeetingId(request.getMeetingId(), request.getMemberId());

        InterestedMeeting deleteInterestedMeeting = new InterestedMeeting(id);

        repository.delete(deleteInterestedMeeting);
    }

}

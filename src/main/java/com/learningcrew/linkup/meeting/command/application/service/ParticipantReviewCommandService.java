package com.learningcrew.linkup.meeting.command.application.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.meeting.command.application.dto.request.ParticipantReviewCreateRequest;
import com.learningcrew.linkup.meeting.command.domain.aggregate.ParticipantReview;
import com.learningcrew.linkup.meeting.command.domain.repository.ParticipantReviewRepository;
import com.learningcrew.linkup.meeting.query.dto.response.MemberDTO;
import com.learningcrew.linkup.meeting.query.service.MeetingParticipationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipantReviewCommandService {
    private final ParticipantReviewRepository repository;
    private final MeetingParticipationQueryService participationQueryService;

    public long createParticipantReview(ParticipantReviewCreateRequest request, int revieweeId, int reviewerId, int meetingId) {
        List<Integer> participants = participationQueryService.getParticipants(meetingId).getParticipants()
                .stream()
                .map(MemberDTO::getMemberId)
                .toList();

        if (!participants.contains(reviewerId) || !participants.contains(revieweeId)) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "유효하지 않은 참가자 평가 요청입니다.");
        }

        // status 5인 경우만 조회해와야 함! (이 부분 아직 구현 안됨)

        // 중복 리뷰 체크 로직 필요

        ParticipantReview review = ParticipantReview.builder()
                .reviewerId(reviewerId)
                .revieweeId(revieweeId)
                .meetingId(meetingId)
                .score(request.getScore())
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(review).getReviewId();
    }


}

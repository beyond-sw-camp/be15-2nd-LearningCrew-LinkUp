package com.learningcrew.linkup.meeting.command.application.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.query.service.MeetingQueryService;
import com.learningcrew.linkup.meeting.command.application.dto.request.ParticipantReviewCreateRequest;
import com.learningcrew.linkup.meeting.command.domain.aggregate.ParticipantReview;
import com.learningcrew.linkup.meeting.command.domain.repository.ParticipantReviewRepository;
import com.learningcrew.linkup.meeting.query.dto.response.MeetingDTO;
import com.learningcrew.linkup.meeting.query.dto.response.ParticipantReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipantReviewCommandService {
    static final int REVIEW_DUE_DAY = 1;
    private static final int MIN_SCORE = 1;
    private static final int MAX_SCORE = 5;

    private final ParticipantReviewRepository repository;
    private final MeetingQueryService meetingQueryService;


    @Transactional
    public long createParticipantReview(ParticipantReviewCreateRequest request, int revieweeId, int reviewerId, int meetingId) {
        List<Integer> participants = participationQueryService.getHistories(meetingId, statusQueryService.getStatusId("DONE") )
                .stream()
                .map(MeetingParticipationDTO::getMemberId)
                .toList();

        if (!participants.contains(reviewerId) || !participants.contains(revieweeId)) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "유효하지 않은 참가자 평가 요청입니다.");
        }

        // 완료된 모임일때만 리뷰 작성 가능
        MeetingDTO meetingDTO = meetingQueryService.getMeeting(meetingId);
        if (!meetingDTO.getStatusType().equals("모임 진행 완료")) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "모임원 평가를 작성할 수 없는 모임입니다.");
        }

        // 평가는 모임 종료 후 1일 이내에 작성해야 함
        LocalDateTime reviewDue = meetingDTO.getDate().plusDays(REVIEW_DUE_DAY).atTime(meetingDTO.getEndTime());
        if (reviewDue.isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "모임원 평가 기간이 지났습니다.");
        }

        // 자기 자신 평가 불가
        if (reviewerId == revieweeId) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "유효하지 않은 참가자 평가 요청입니다.");
        }

        // 평가 점수 유효성 검사
        if (request.getScore() < MIN_SCORE || request.getScore() > MAX_SCORE) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "유효하지 않은 평가 점수입니다.");
        }

        // 중복 리뷰 체크 로직
        ParticipantReviewDTO reviewDTO = participantReviewQueryService.getReview(meetingId, reviewerId, revieweeId);
        if (reviewDTO != null) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "이미 리뷰 등록된 사용자입니다.");
        }


        // 리뷰 등록
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

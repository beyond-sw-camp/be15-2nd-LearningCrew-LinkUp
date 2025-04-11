package com.learningcrew.linkup.meeting.query.service;

import com.learningcrew.linkup.exception.BusinessException;
import com.learningcrew.linkup.exception.ErrorCode;
import com.learningcrew.linkup.linker.command.domain.repository.MemberRepository;
import com.learningcrew.linkup.linker.command.domain.service.MemberDomainService;
import com.learningcrew.linkup.linker.query.mapper.MemberMapper;
import com.learningcrew.linkup.meeting.query.dto.response.*;
import com.learningcrew.linkup.meeting.query.mapper.MeetingParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingParticipationQueryService {

    private final MeetingParticipationMapper mapper;
    private final StatusQueryService statusQueryService;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /* 모임에 속한 참가자 전체 조회 */
    @Transactional(readOnly = true)
    public List<MemberDTO> getParticipantsByMeetingId(int meetingId) {
        int statusId = statusQueryService.getStatusId("ACCEPTED");
        List<MeetingParticipationDTO> histories = getHistories(meetingId, statusId);

        List<Integer> memberIds = histories.stream()
                .map(MeetingParticipationDTO::getMemberId)
                .toList();

        /* MSA 분리 시 멤버에서 호출해오기 */
        return memberIds.stream()
                .map(id -> memberRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)))
                .map(x -> modelMapper.map(x, MemberDTO.class))
                .toList();
    }

    /* 모임에 속한 참가 내역 status별 조회 */
    @Transactional(readOnly = true)
    public List<MeetingParticipationDTO> getHistories(int meetingId, int statusId) {
        return mapper.selectHistoriesByMeetingIdAndStatusId(meetingId, statusId);
    }

    @Transactional(readOnly = true)
    public List<MemberDTO> getParticipants(int meetingId, int memberId) {
        List<MemberDTO> response = mapper.selectParticipantsByMeetingId(meetingId);
        MeetingParticipationDTO participation = mapper.selectMeetingParticipationByMeetingIdAndMemberId(meetingId, memberId);

        if (participation == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "해당 모임에 존재하지 않는 회원입니다.");
        }

        return response;
    }

    @Transactional(readOnly = true)
    public MeetingParticipationResponse getParticipation(int meetingId, int memberId) {
        MeetingParticipationDTO response = mapper.selectMeetingParticipationByMeetingIdAndMemberId(meetingId, memberId);

        return MeetingParticipationResponse.builder()
                .participationId(response.getParticipationId())
                .build();
    }
}

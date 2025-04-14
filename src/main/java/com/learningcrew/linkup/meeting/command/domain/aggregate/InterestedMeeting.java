package com.learningcrew.linkup.meeting.command.domain.aggregate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "interested_meeting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InterestedMeeting {

    @EmbeddedId
    private InterestedMeetingId interestedMeetingId;

    public InterestedMeeting(InterestedMeetingId id) {
        this.interestedMeetingId = id;
    }

    // 편의 메서드 추가 (필요할 경우)
    public int getMeetingId() {
        return interestedMeetingId.getMeetingId();
    }

    public int getMemberId() {
        return interestedMeetingId.getMemberId();
    }
}

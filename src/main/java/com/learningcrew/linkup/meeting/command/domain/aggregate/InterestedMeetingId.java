package com.learningcrew.linkup.meeting.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class InterestedMeetingId implements Serializable {
    @Column(name = "meeting_id")
    private int meetingId;
    @Column(name = "member_id")
    private int memberId;
}
package com.learningcrew.linkup.meeting.command.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_participation_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class MeetingParticipationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long participationId;
    private int memberId;
    private int meetingId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime participatedAt;
    @Setter
    private int statusId;

}
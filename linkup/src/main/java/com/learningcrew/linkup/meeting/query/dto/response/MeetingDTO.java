package com.learningcrew.linkup.meeting.query.dto.response;

import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class MeetingDTO {
    private int meetingId;
    private int leaderId;
    private Integer placeId;
    private int sportId;
    private int statusId;
    private String meetingTitle;
    private String meetingContent;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int minUser;
    private int maxUser;
    private MeetingGender gender;
    private String ageGroup;
    private String level;
    private String customPlaceAddress;
    private Double latitude;
    private Double longitude;
}

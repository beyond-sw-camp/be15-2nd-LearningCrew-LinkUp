package com.learningcrew.linkup.meeting.command.application.dto.request;

import com.learningcrew.linkup.meeting.command.domain.aggregate.MeetingGender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class MeetingCreateRequest {
    @Min(value = 1)
    private int leaderId;
    @Min(value = 1)
    private Integer placeId;
    @Min(value = 1)
    private int sportId;
    @NotBlank
    private String meetingTitle;
    @NotBlank
    private String meetingContent;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int minUser;
    private int maxUser;
    private MeetingGender gender;
    @NotBlank
    private String ageGroup;
    @NotBlank
    private String level;
    private String customPlaceAddress;
    private Double latitude;
    private Double longitude;
}

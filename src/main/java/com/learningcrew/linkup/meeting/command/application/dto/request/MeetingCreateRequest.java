package com.learningcrew.linkup.meeting.command.application.dto.request;

import com.learningcrew.linkup.linker.command.domain.constants.LinkerGender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class MeetingCreateRequest {
    @Min(value = 1)
    private int leaderId;
    @Min(value = 1)
    private Integer placeId;
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
    private LinkerGender gender;
    @NotBlank
    private String ageGroup;
    @NotBlank
    private String level;
    private String customPlaceAddress;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;
    private int statusId = 1;
}

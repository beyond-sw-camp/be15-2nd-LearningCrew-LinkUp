package com.learningcrew.linkup.place.command.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class OperationTimeRequest {
    private String dayOfWeek;   // 예: "MON", "TUE", "WED", 등
    private LocalTime startTime; // 예: 09:00:00
    private LocalTime endTime;   // 예: 21:00:00
}
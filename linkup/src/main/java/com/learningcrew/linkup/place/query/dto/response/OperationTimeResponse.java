package com.learningcrew.linkup.place.query.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class OperationTimeResponse {
    private String dayOfWeek;  // MON, TUE, ...
    private LocalTime startTime;
    private LocalTime endTime;
}


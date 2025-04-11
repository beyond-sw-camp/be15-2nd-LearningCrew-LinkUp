package com.learningcrew.linkup.place.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class ReserveDto {
    private Integer reservationId;
    private String placeName;
    private String statusType;
    private Date reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
}

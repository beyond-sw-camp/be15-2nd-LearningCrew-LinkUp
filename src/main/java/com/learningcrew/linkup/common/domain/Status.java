package com.learningcrew.linkup.common.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status")
@Getter
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    private String statusType;

    public static Status of(int statusId, String statusType) {
        Status status = new Status();
        status.setStatusId(statusId);
        status.setStatusType(statusType);
        return status;
    }
}

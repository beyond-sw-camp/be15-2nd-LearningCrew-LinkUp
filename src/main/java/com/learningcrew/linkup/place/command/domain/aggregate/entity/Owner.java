package com.learningcrew.linkup.place.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@Entity
@Table(name = "owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Owner {

    @Id
    @Column(name = "owner_id")
    private int ownerId;  // ownerId가 user의 id와 동일함

    private int statusId;
    private String businessRegistrationDocumentUrl;
    private Date authorizedAt;
    private String rejectionReason;
}

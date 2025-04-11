package com.learningcrew.linkup.place.command.domain.aggregate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private int placeId;

    // 오너 테이블 참조용 FK
    @Column(name = "owner_id")
    private int ownerId;

    // 운동 종목(sport_type 등) 테이블 참조용 FK
    @Column(name = "sport_id")
    private int sportId;

    private String placeName;
    private String address;
    private String description;
    private String equipment;
    private char isActive;  // 'Y'/'N'
    @CreatedDate
    private LocalDateTime createdAt;
    private int rentalCost;


    public void updatePlaceDetails(int sportId, @NotBlank String placeName, @NotBlank String address, String description, String equipment, char isActive, int rentalCost) {
        this.sportId = sportId;
        this.placeName = placeName;
        this.address = address;
        this.description = description;
        this.equipment = equipment;
        this.isActive = isActive;
        this.rentalCost = rentalCost;
    }
}

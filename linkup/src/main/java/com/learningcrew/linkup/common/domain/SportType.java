package com.learningcrew.linkup.common.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sport_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of") // Optional한 생성자 팩토리
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sportTypeId;

    private String sportName;
}

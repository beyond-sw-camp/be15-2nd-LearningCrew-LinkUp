package com.learningcrew.linkup.place.command.domain.aggregate.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode  // 기본적으로 모든 필드를 기반으로 equals & hashCode 생성
public class FavoriteId implements Serializable {
    private int memberId;
    private int placeId;
}

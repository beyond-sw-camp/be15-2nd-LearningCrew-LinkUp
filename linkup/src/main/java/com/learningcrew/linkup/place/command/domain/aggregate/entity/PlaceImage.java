package com.learningcrew.linkup.place.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="place_image")
@NoArgsConstructor()
@Getter
@Setter
public class PlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Column(name = "place_id")
    private int placeId;

    private String imageUrl;

}

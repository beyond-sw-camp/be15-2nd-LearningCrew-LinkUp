package com.learningcrew.linkup.place.command.infrastructure.repository;

import com.learningcrew.linkup.place.command.domain.aggregate.entity.Place;
import com.learningcrew.linkup.place.command.domain.repository.PlaceRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlaceRepository extends PlaceRepository, JpaRepository<Place, Integer> {
}

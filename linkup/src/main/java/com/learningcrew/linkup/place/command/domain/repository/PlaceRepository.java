package com.learningcrew.linkup.place.command.domain.repository;

import com.learningcrew.linkup.place.command.domain.aggregate.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place save(Place place);
    Optional<Place> findById(int placeId);
}

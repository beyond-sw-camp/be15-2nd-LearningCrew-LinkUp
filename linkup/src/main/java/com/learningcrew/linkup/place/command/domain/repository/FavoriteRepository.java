package com.learningcrew.linkup.place.command.domain.repository;

import com.learningcrew.linkup.place.command.domain.aggregate.entity.Favorite;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.FavoriteId;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    Favorite save(Favorite favorite);
}

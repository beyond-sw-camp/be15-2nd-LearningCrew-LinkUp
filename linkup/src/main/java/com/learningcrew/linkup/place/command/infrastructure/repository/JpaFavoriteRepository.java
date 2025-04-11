package com.learningcrew.linkup.place.command.infrastructure.repository;

import com.learningcrew.linkup.place.command.domain.aggregate.entity.Favorite;
import com.learningcrew.linkup.place.command.domain.aggregate.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JpaFavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    // 여기서 기본 CRUD 기능이 즐겨찾기 등록, 삭제 등에 사용될 수 있습니다.
}

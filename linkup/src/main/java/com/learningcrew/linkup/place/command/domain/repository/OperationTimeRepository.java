package com.learningcrew.linkup.place.command.domain.repository;

import com.learningcrew.linkup.place.command.domain.aggregate.entity.OperationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface OperationTimeRepository extends JpaRepository<OperationTime, Integer> {
    Optional<OperationTime> findByPlaceIdAndDayOfWeek(int placeId, String dayOfWeek);
}


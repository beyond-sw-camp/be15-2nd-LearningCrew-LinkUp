package com.learningcrew.linkup.notification.command.domain.repository;

import com.learningcrew.linkup.notification.command.domain.aggregate.NotificationDomainType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDomainTypeRepository extends JpaRepository<NotificationDomainType, Integer> {
}
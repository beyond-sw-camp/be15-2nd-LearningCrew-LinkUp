package com.learningcrew.linkup.notification.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "domain_type")
public class NotificationDomainType {

    @Id
    @Column(name = "domain_type_id")
    private Integer id;

    // 도메인 이름 (예: FRIEND, COMMUNITY 등)
    @Column(name = "domain_name", nullable = false)
    private String name;
}
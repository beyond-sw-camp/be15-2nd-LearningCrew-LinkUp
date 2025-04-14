package com.learningcrew.linkup.common.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String roleName;

    public static Role of(int roleId, String roleName) {
        Role role = new Role();
        role.roleId = roleId;
        role.roleName = roleName;
        return role;
    }
}

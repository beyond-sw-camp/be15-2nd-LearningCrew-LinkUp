package com.learningcrew.linkup.linker.command.domain.aggregate;

import com.learningcrew.linkup.common.domain.Role;
import com.learningcrew.linkup.common.domain.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;


    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private String userName;
    private String password;
    private String contactNumber;
    private String email;
    private int pointBalance = 0;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name="status_id", nullable = false)
    private Status status;

    /* 비밀번호 암호화 */
    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    /* 권한 주입 */
    public void setRole(Role role) {
        this.role = role;
    }

    /* 상태 주입 */
    public void setStatus(Status status) {
        this.status = status;
    }
}

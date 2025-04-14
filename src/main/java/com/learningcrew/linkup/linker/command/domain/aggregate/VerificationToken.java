package com.learningcrew.linkup.linker.command.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "verification_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationToken {
    @Id
    private int userId;
    private String email;
    private String code;
    private LocalDateTime expiryDate;
    private String tokenType;
}

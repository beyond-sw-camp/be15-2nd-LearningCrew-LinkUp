package com.learningcrew.linkup.report.query.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistDTO {

    private Long memberId;
    private String userName;
    private String reason;
    private LocalDateTime createdAt;
}

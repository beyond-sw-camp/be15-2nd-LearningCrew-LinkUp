package com.learningcrew.linkup.report.query.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportUserScoreDTO {

    private Long memberId;
    private String memberName;
    private Long reportScore;
}

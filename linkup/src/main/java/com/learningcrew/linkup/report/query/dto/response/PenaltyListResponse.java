package com.learningcrew.linkup.report.query.dto.response;

import com.learningcrew.linkup.common.dto.Pagination;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyListResponse {

    private List<PenaltyDTO> penalties;
    private Pagination pagination;
}

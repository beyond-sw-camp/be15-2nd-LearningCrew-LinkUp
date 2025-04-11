package com.learningcrew.linkup.meeting.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MemberDTO {
    private int memberId;
    private String gender;
    private String nickname;
    private LocalDate birthDate;
    private String introduction;
    private BigDecimal memberTempurature; // 테이블 컬럼명과 맞춤
    private String profileImageUrl;
//    private int statusId;


}

package com.learningcrew.linkup.linker.command.domain.aggregate;

import com.learningcrew.linkup.linker.command.domain.constants.LinkerGender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "UserMember")
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    private int memberId;

    @Enumerated(EnumType.STRING)
    private LinkerGender gender;

    private String nickname;

    private LocalDate birthDate;

    private String introduction;
    @Column(name = "member_tempurature") // DB와 연동하기 위한 의도적 오타
    private BigDecimal mannerTemperature = BigDecimal.ZERO;
    private String profileImageUrl;

    public void setProfileImageUrl(String defaultProfileImage) {
        this.profileImageUrl = defaultProfileImage;
    }

    public void setMemberId(int userId) {
        this.memberId = userId;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }
}

package com.learningcrew.linkup.place.command.domain.aggregate.entity;
;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "favorite")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class) // 필요 시 Auditing 정보를 넣을 수 있음
public class Favorite {

    @EmbeddedId
    private FavoriteId id;

    // 추가적으로 등록 일시 같은 필드를 넣을 수도 있습니다.
    // 예:
    // @CreatedDate
    // private LocalDateTime createdAt;

    // 편의 생성자: memberId와 placeId를 받아 복합키를 설정합니다.
    public Favorite(int memberId, int placeId) {
        this.id = new FavoriteId(memberId, placeId);
    }
}

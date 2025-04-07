package ahnsozero.moda.util;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class) // 생성될때/수정될때 자동으로 컬럼에 데이터를 채워주는 역할
@MappedSuperclass // 공통 맵핑 정보를 상속받아서 사용가능하게 해줌
@Getter
public class TimeBaseEntity {
    @CreatedDate
    @Column(name = "create_date", updatable = false) //jpa에서 한 번만 DB에 저장되고, 그 이후엔 절대 수정되지 않도록 막는 설정
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @Column(name = "is_delete")
    private boolean isDelete = false;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifyDate = LocalDateTime.now();
    }
}
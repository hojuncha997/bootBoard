package com.example.bootboard.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;

}


/*
* @MappedSuperclass:
* 이 어노테이션이 적용되면 적용되면 테이블로 생성되지 않는다.
* 실제 테이블은 BaseEntity클래스를 상속한 엔티티의 클래스로 DB 테이블이 생성된다.
*
* @CreatedDate:
* JPA에서 엔티티의 생성 시간을 처리한다.
*
* @LastModifiedDate:
* 최종시간을 자동으로 처리하는 용도로 사용된다.
* 칼럼의 속성으로는 insertable, updatable이 있다. 여기서는 updatable=false로 지정했기 때문에
* 해당 엔티티 객체를 DB에 반영할 때 regDate 칼럼은 변경되지 않는다. 생성될 때만 기록되야 하니 당연하다.
*
*
*
 */







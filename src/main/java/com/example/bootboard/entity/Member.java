package com.example.bootboard.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{

    @Id
    private String email;

    private String password;

    private String name;
}

/*
* Member 클래스는 이메일 주소를 PK로 이용한다.
* DB설계에서도 member 테이블은 PK만을 가지고 있고, FK를 사용하지 않는다.
* 따라서 별도의 참조가 필요하지 않다.
*
* */

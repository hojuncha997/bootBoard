//Board.java
package com.example.bootboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY) //명시적으로 Lazy Loading 지정
    private Member writer; //FK 연관관계 지정

    //Board클래스는 Member클래스의 email(PK)를 FK로 참조하는 구조이다.

    //수정을 위한 메서드
    //제목 수정
    public void changeTitle(String title) {
        this.title = title;
    }
    //내용 수정
    public void changeContent(String content) {
        this.content = content;
    }

}

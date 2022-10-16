//ReplyDTO.java

package com.example.bootboard.dto;

//ReplyDTO는 Reply 엔티티 클래스와 유사. 그러나 게시물 번호만을 가지는 형태로 작성

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

    private Long rno;

    private String text;

    private String replyer;

    private Long bno; //게시글 번호

    private LocalDateTime regDate, modDate;

}
//ReplyDTO를 Reply 엔티티로 만들거나 그 반대 작업을 위해 서비스 클래스 생성
//ReplyService인터페이스, ReplyServiceImpl 구현 클래스
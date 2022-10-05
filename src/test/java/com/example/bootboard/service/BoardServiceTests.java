//BoardServiceTests.java

package com.example.bootboard.service;

import com.example.bootboard.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test ...")
                .writerEmail("user55@aaa.com") //현재 DB에 존재한느 회원 이메일
                .build();

        Long bno = boardService.register(dto);
    }

}

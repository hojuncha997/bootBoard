//BoardRepositoryTests.java
package com.example.bootboard.repository;

import com.example.bootboard.entity.Board;
import com.example.bootboard.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    public void testRead1(){
        Optional<Board> result =boardRepository.findById(100L); //DB에 존재한는 번호

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

}

/*
* testInsert()는 한 명의 사용자가 하나의 게시물을 등록하도록 작성되었다.
* */
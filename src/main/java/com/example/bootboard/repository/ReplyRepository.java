//ReplyRepository.java

package com.example.bootboard.repository;

import com.example.bootboard.entity.Board;
import com.example.bootboard.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //삭제처리 추가
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno ")
    void deleteByBno(Long bno);

    //ch6 게시물로 댓글 목록 가져오기
    //Board객체를 받아 모든 댓글을 순번대로 가져옴.
    List<Reply> getRepliesByBoardOrderByRno(Board board);

}

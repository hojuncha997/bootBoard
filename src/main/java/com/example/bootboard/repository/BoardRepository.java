//BoardRepository.java

package com.example.bootboard.repository;


import com.example.bootboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 한 개의 로우(Object) 내에 Object[] 로 나옴
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);
    //Board를 사용하지만 Member를 같이 조회해야 하는 상황
    //Board 클래스에는 Member와의 연관관계가 있으므로 b.writer와 같은 형태로 사용한다.
    // 이처럼 내부에 있는 엔티티를 이용할 때는 'LEFT JOIN' 뒤에 'ON'을 이용하는 부분이 없다.

    //ON을 사용하여 Reply와 조인
    @Query("SELECT b, r FROM Board b LEFT OUTER JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);


    @Query(value = "SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT JOIN b.writer w " +
            " LEFT JOIN Reply r ON r.board = b " +
            " GROUP BY b",
            countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable); // 목록 화면에 필요한 데이터


    @Query("SELECT b, w, count(r) " +
            " FROM Board b LEFT JOIN b.writer w " +
            " LEFT OUTER JOIN Reply r ON r.board = b " +
            " WHERE b.bno = :bno")

    Object getBoardByBno(@Param("bno") Long bno);

}

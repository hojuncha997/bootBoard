package com.example.bootboard.repository.search;

import com.example.bootboard.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.bootboard.entity.QBoard.board;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport
        implements SearchBoardRepository {

    //생성자
    public SearchBoardRepositoryImpl(){
        super(Board.class); //여기서 super는 querydslRepositorySupport이다.
    }

    @Override
    public Board search1() {
        log.info("search1............");
        //JPQL 사용
        //이제 로그가 찍힐 뿐만 아니라 아래의 JPQL도 실행된다.

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        //집합함수 처리 : groupBy()

        //튜플 사용!!!!!
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());
        tuple.groupBy(board);

//        jpqlQuery.select(board, member.email, reply.count())
//                .groupBy(board);

        log.info("------------------------------");
        log.info(tuple); //jpqlQuery -> tuple
        log.info("------------------------------");

        List<Tuple> result = tuple.fetch();
//        List<Board> result = jpqlQuery.fetch();

        log.info(result);

        return null;
    }



//        조인 처리
//        QBoard board = QBoard.board;
//        QReply reply = QReply.reply;
//
//        JPQLQuery<Board> jpqlQuery = from(board);
//        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
//
//        List<Board> result = jpqlQuery.fetch();
//        return null;
//    }



//        //Q도메인의 QBoard
//        QBoard board = QBoard.board;

//        JPQLQuery<Board> jpqlQuery = from(board);
//        jpqlQuery.select(board).where(board.bno.eq(3L)); //bno가 3번인 데이터를 select
//
//        log.info("------------------------------");
//        log.info(jpqlQuery);
//        log.info("------------------------------");
//
//        List<Board> result = jpqlQuery.fetch();
//        return null;
//    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("searchPage............................");

        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        //SELECT b, w, count(r) FROM Board b
        //LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);

        if(type != null){
            String[] typeArr = type.split("");
            //검색 조건 작성
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String t:typeArr) {
                switch (t){
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }

        tuple.where(booleanBuilder);
        tuple.groupBy(board);

        List<Tuple> result = tuple.fetch();

        log.info(result);

        return null;

    }



}

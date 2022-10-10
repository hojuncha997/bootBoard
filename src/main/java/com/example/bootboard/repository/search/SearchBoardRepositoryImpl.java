package com.example.bootboard.repository.search;

import com.example.bootboard.entity.Board;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport
        implements SearchBoardRepository {

    //생성자
    public SearchBoardRepositoryImpl(){
        super(Board.class); //여기서 super는 querydslRepositorySupport이다.
    }

    @Override
    public Board search1(){
        log.info("search1............");
        return null;
    }
}

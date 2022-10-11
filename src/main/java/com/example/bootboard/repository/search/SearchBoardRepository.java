//SearchBoardRepository.java

package com.example.bootboard.repository.search;

import com.example.bootboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    Board search1();

    //Page<Object[]> 처리
    Page<Object[]> searchPage(String type, String Keyword, Pageable pageable);
}

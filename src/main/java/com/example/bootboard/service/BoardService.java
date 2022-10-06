//BoardService.java

package com.example.bootboard.service;

import com.example.bootboard.dto.BoardDTO;
import com.example.bootboard.dto.PageRequestDTO;
import com.example.bootboard.dto.PageResultDTO;
import com.example.bootboard.entity.Board;
import com.example.bootboard.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    //getList() : 목록처리
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    //entityToDTO()
    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) //long으로 나오므로 int처리
                .build();

        return boardDTO;
    }

}




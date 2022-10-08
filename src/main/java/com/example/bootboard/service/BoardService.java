//BoardService.java

package com.example.bootboard.service;

import com.example.bootboard.dto.BoardDTO;
import com.example.bootboard.dto.PageRequestDTO;
import com.example.bootboard.dto.PageResultDTO;
import com.example.bootboard.entity.Board;
import com.example.bootboard.entity.Member;

public interface BoardService {
    //등록처리
    Long register(BoardDTO dto);

    //getList() : 목록처리
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    //조회 처리
    BoardDTO get(Long bno);

    //게시글 삭제 기능
    void removeWithReplies(Long bno);

    //수정 처리
    void modify(BoardDTO boardDTO);



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




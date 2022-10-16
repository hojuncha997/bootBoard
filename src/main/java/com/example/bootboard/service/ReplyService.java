package com.example.bootboard.service;

import com.example.bootboard.dto.ReplyDTO;
import com.example.bootboard.entity.Board;
import com.example.bootboard.entity.Reply;

import java.util.List;

public interface ReplyService {

    //댓글의 등록
    Long register(ReplyDTO replyDTO);

    //특정 게시물의 댓글 목록
    List<ReplyDTO> getList(Long bno);

    //댓글 수정
    void modify(ReplyDTO replyDTO);

    //댓글 삭제
    void remove(Long rno);

    //ReplyDTO를 Reply로 변환하는 dtoToEntity()
    //ReplyDTO를 Reply객체로 변환 Board객체의 처리가 수반됨
    default Reply dtoToEntity(ReplyDTO replyDTO){
        Board board = Board.builder().bno(replyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }

    //Reply를 ReplyDTO를 변환하는 기능 : entityToDTO()
    //Reply 객체를 ReplyDTO로 변환. Board객체가 필요하지 않으므로 게시물 번호만 필요
    default ReplyDTO entityToDTO(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return dto;
    }




}

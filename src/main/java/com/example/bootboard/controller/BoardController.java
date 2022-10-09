//BoardController.java
package com.example.bootboard.controller;

import com.example.bootboard.dto.BoardDTO;
import com.example.bootboard.dto.PageRequestDTO;
import com.example.bootboard.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //목록
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list............." + pageRequestDTO);
        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    //등록(get)
    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }
    //등록(post)
    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes){

        log.info("dto..." + dto);
    //새로 추가된 엔티티의 번호
        Long bno = boardService.register(dto);

        log.info("BNO: " + bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    //조회 + 접근 가능 경로에 /modify (get)를 추가하였다.
    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                     Long bno, Model model) {

        log.info("bno: " + bno);
        BoardDTO boardDTO = boardService.get(bno);

        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
    }

    //삭제
    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {

        log.info("bno: " + bno);

        boardService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        //삭제 뒤 목록 페이지롱 이동
        return "redirect:/board/list";
    }

    //수정(post)
    @PostMapping("/modify")
    public String modify(BoardDTO dto
            , @ModelAttribute("requestDTO") PageRequestDTO requestDTO
            , RedirectAttributes redirectAttributes) {

        log.info("post modify.................");
        log.info("dto: " + dto);

        boardService.modify(dto);
        //모델에 추가
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        redirectAttributes.addAttribute("bno", dto.getBno());
        //수정 뒤 조회 페이지로 이동
        return "redirect:/board/read";
    }

}

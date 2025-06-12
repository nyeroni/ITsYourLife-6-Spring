package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    final private BoardService boardService;

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", boardService.getList());
    }

    @GetMapping("/create")
    public void create() {
        log.info("create");
    }

    @PostMapping("/create")
    public String create(BoardDTO boardDTO) {
        log.info("create : " + boardDTO);
        boardService.create(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping({ "/get", "/update" })
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or /update");
        model.addAttribute("board", boardService.get(no));
    }

    @PostMapping("/update")
    public String update(BoardDTO board) {
        log.info("update:" + board);
        boardService.update(board);
        return "redirect:/board/list";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {

        log.info("delete..." + no);
        boardService.delete(no);
        return "redirect:/board/list";

    }
}

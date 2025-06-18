package org.scoula.board.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.weather.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String update(BoardDTO board, RedirectAttributes ra) {
        log.info("update:" + board);
        if (boardService.update(board)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no, RedirectAttributes ra) {

        log.info("delete..." + no);
        if (boardService.delete(no)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";

    }
    /**
     * 파일 다운로드 처리
     * @param no 첨부파일 번호
     * @param response HTTP 응답 객체
     * @throws Exception
     */
    @GetMapping("/download/{no}")
    @ResponseBody  // View를 사용하지 않고 직접 응답 데이터 전송
    public void download(@PathVariable("no") Long no,
                         HttpServletResponse response) throws Exception {

        // 첨부파일 정보 조회
        BoardAttachmentVO attach = boardService.getAttachment(no);

        // 실제 파일 객체 생성
        // (java.io.File)
        File file = new File(attach.getPath());

        // 파일 다운로드 처리
        UploadFiles.download(response, file, attach.getFilename());
    }
}

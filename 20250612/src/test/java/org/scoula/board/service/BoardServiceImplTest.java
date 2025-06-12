package org.scoula.board.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void getList() {
        for(BoardDTO boardDTO : boardService.getList()) {
            log.info(boardDTO);
        }
    }

    @Test
    public void get() {
        log.info(boardService.get(1L));
    }

    @Test
    public void create() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("새로 작성하는 글");
        boardDTO.setContent("새로 작성하는 내용");
        boardDTO.setWriter("User1");

        boardService.create(boardDTO);
        log.info("생성된 게시물의 번호 : " + boardDTO.getNo());
    }

    @Test
    public void update() {
        BoardDTO boardDTO = boardService.get(1L);
        boardDTO.setTitle("제목 수정합니다.");
        log.info("update RESULT : " + boardService.update(boardDTO));

    }

    @Test
    public void delete() {
        log.info("delete RESULT : " + boardService.delete(2L));
    }
}
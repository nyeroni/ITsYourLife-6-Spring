package org.scoula.board.mapper;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
@Log4j2
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("BoardMapper의 목록 불러오기")
    void getList() {
        for(BoardVO boardVO : boardMapper.getList()) {
            log.info(boardVO);
        }
    }

    @Test
    @DisplayName("BoardMapper의 게시글 읽기")
    public void get() {
        BoardVO boardVO = boardMapper.get(1L);
        log.info(boardVO);
    }

    @Test
    @DisplayName("BoardMapper의 새 글 작성")
    public void create() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성하는 글");
        boardVO.setContent("새로 작성하는 내용");
        boardVO.setWriter("User0");

        boardMapper.create(boardVO);
        log.info(boardVO);
    }

    @Test
    @DisplayName("BoardMapper의 글 수정")
    public void update() {
        BoardVO boardVO = new BoardVO();
        boardVO.setNo(5L);
        boardVO.setTitle("수정된 제목");
        boardVO.setContent("수정된 내용");
        boardVO.setWriter("User0");

        int count = boardMapper.update(boardVO);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    @DisplayName("BoardMapper의 글 삭제")
    public void delete() {
        log.info("DELETE COUNT: " + boardMapper.delete(3L));
    }
}
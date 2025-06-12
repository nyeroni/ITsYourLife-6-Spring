package org.scoula.board.service;

import java.util.List;
import org.scoula.board.dto.BoardDTO;

public interface BoardService {
    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public void create(BoardDTO boardDTO);
    public boolean update(BoardDTO boardDTO);
    public boolean delete(Long no);
}

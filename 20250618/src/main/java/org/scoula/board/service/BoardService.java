package org.scoula.board.service;

import java.util.List;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.weather.dto.BoardDTO;

public interface BoardService {
    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public void create(BoardDTO boardDTO);
    public boolean update(BoardDTO boardDTO);
    public boolean delete(Long no);
    public BoardAttachmentVO getAttachment(Long no);
    public boolean deleteAttachment(Long no);
}

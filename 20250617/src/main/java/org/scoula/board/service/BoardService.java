package org.scoula.board.service;

import java.util.List;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;

public interface BoardService {
    public List<BoardDTO> getList();
    public BoardDTO get(Long no);
    public BoardDTO create(BoardDTO boardDTO);
    public BoardDTO update(BoardDTO boardDTO);
    public BoardDTO delete(Long no);
    public BoardAttachmentVO getAttachment(Long no);
    public boolean deleteAttachment(Long no);
}

package org.scoula.board.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final static String BASE_DIR = "/Users/yerong/documents/board";
    final private BoardMapper boardMapper;
    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");
        return boardMapper.getList().stream()
                .map(BoardDTO::of)
                        .toList();
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get..........");
        BoardDTO boardDTO = BoardDTO.of(boardMapper.get(no));
        return Optional.ofNullable(boardDTO).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public BoardDTO create(BoardDTO boardDTO) {
        log.info("create........." + boardDTO);

        BoardVO vo = boardDTO.toVo();
        boardMapper.create(vo);
        boardDTO.setNo(vo.getNo());
        List<MultipartFile> files = boardDTO.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(vo.getNo(), files);  // 게시글 번호가 필요하므로 게시글 등록 후 처리
        }
        return get(vo.getNo());
    }

    @Override
    public BoardDTO update(BoardDTO boardDTO) {
        log.info("update........." + boardDTO);
        boardMapper.update(boardDTO.toVo());
        return get(boardDTO.getNo());
    }

    @Override
    public BoardDTO delete(Long no) {
        log.info("delete........." + no);
        BoardDTO board = get(no);
        boardMapper.delete(no);
        return board;
    }
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return boardMapper.getAttachment(no);
    }

    // 첨부파일 삭제
    @Override
    public boolean deleteAttachment(Long no) {
        return boardMapper.deleteAttachment(no) == 1;
    }
    /**
     * 파일 업로드 처리 (private 메서드)
     * @param bno 게시글 번호
     * @param files 업로드할 파일 목록
     */
    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part : files) {
            // 빈 파일은 건너뛰기
            if (part.isEmpty()) continue;

            try {
                // 파일을 서버에 저장
                String uploadPath = UploadFiles.upload(BASE_DIR, part);

                // 첨부파일 정보를 DB에 저장
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                boardMapper.createAttachment(attach);

            } catch (IOException e) {
                // @Transactional이 감지할 수 있도록 RuntimeException으로 변환
                throw new RuntimeException(e);
            }
        }
    }
}

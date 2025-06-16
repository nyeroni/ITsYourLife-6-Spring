package org.scoula.board.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.common.util.UploadFiles;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardAttachmentVO {
    private Long no; //
    private Long bno;
    private String filename;
    private String path;
    private String contentType;
    private Long size;
    private Date regDate;

    public static BoardAttachmentVO of(MultipartFile part, Long bno, String path) {
        return builder()
                .bno(bno)
                .filename(part.getOriginalFilename())
                .path(path)
                .contentType(part.getContentType())
                .size(part.getSize())
                .build();
    }
    /**
     * 파일 크기를 사용자 친화적 형태로 변환
     * @return 포맷된 파일 크기 (예: 1.2 MB)
     */
    public String getFileSize() {
        return UploadFiles.getFormatSize(size);
    }
}

package com.ssafy.faraway.domain.post.service.dto;

import com.ssafy.faraway.common.domain.UploadFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SavePostDto {
    private String title;
    private String content;
    private Long memberId;
    private Long categoryId;
    private List<UploadFile> uploadFiles;

    @Builder
    public SavePostDto(String title, String content, Long memberId, Long categoryId, List<UploadFile> uploadFiles) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.uploadFiles = uploadFiles;
    }
}

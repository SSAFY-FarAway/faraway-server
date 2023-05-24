package com.ssafy.faraway.domain.post.service.dto;

import com.ssafy.faraway.common.domain.UploadFile;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdatePostDto {
    private String title;
    private String content;
    private Long postId;
    private List<Long> deleteAttachmentIds;
    private List<UploadFile> uploadFiles;

    @Builder
    public UpdatePostDto(String title, String content, Long postId, List<Long> deleteAttachmentIds, List<UploadFile> uploadFiles) {
        this.title = title;
        this.content = content;
        this.postId = postId;
        this.deleteAttachmentIds = deleteAttachmentIds == null ? new ArrayList<>() : deleteAttachmentIds;
        this.uploadFiles = uploadFiles;
    }
}

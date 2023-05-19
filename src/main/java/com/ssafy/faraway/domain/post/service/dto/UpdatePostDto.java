package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePostDto {
    private String title;
    private String content;
    private List<Long> deleteAttachmentIds;

    @Builder
    public UpdatePostDto(String title, String content, List<Long> deleteAttachmentIds) {
        this.title = title;
        this.content = content;
        this.deleteAttachmentIds = deleteAttachmentIds;
    }
}

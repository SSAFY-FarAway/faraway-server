package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SavePostCommentDto {
    private String content;

    @Builder
    public SavePostCommentDto(String content) {
        this.content = content;
    }
}

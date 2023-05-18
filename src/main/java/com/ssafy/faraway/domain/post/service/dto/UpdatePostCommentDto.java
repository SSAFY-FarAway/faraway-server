package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePostCommentDto {
    private String content;

    @Builder
    public UpdatePostCommentDto(String content) {
        this.content = content;
    }
}

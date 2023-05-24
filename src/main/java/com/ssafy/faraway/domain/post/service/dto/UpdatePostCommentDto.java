package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePostCommentDto {
    private Long commentId;
    private String content;

    @Builder
    public UpdatePostCommentDto(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}

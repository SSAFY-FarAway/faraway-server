package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePostCommentDto {
    private Long commentId;
    private String content;

    @Builder
    public UpdatePostCommentDto(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}

package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SavePostCommentDto {
    private Long postId;
    private Long memberId;
    private String content;

    @Builder
    public SavePostCommentDto(Long postId, Long memberId, String content) {
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
    }
}

package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SavePostLikeDto {
    private Long postId;
    private Long memberId;

    @Builder
    public SavePostLikeDto(Long postId, Long memberId) {
        this.postId = postId;
        this.memberId = memberId;
    }
}

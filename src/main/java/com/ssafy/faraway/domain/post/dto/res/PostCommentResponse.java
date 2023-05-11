package com.ssafy.faraway.domain.post.dto.res;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCommentResponse {
    private Long id;
    private Long postId;
    private Long memberId;
    private String loginId;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public PostCommentResponse(Long id, Long postId, Long memberId, String loginId, String content, LocalDateTime createdDate) {
        this.id = id;
        this.postId = postId;
        this.memberId = memberId;
        this.loginId = loginId;
        this.content = content;
        this.createdDate = createdDate;
    }
}

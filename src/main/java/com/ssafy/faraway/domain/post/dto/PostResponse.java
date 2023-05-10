package com.ssafy.faraway.domain.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String categoryName;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;

    @Builder
    public PostResponse(Long id, Long memberId, String loginId, String categoryName, String title, String content, int hit, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.categoryName = categoryName;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate;
    }
}

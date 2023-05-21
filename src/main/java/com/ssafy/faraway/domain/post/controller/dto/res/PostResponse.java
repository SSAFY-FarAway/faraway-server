package com.ssafy.faraway.domain.post.controller.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String categoryName;
    private String title;
    private int hit;
    private int likeCnt;
    private LocalDateTime createdDate;

    @Builder
    public PostResponse(Long id, Long memberId, String loginId, String categoryName, String title, int hit, int likeCnt, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.categoryName = categoryName;
        this.title = title;
        this.hit = hit;
        this.likeCnt = likeCnt;
        this.createdDate = createdDate;
    }
}

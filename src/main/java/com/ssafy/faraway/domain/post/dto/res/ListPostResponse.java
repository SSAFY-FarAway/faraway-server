package com.ssafy.faraway.domain.post.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ListPostResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String categoryName;
    private String title;
    private int hit;
    private LocalDateTime createdDate;

    @Builder
    public ListPostResponse(Long id, Long memberId, String loginId, String categoryName, String title, int hit, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.categoryName = categoryName;
        this.title = title;
        this.hit = hit;
        this.createdDate = createdDate;
    }
}

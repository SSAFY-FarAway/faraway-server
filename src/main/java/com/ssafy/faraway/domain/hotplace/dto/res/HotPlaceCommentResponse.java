package com.ssafy.faraway.domain.hotplace.dto.res;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotPlaceCommentResponse {
    private Long id;
    private Long hotPlaceId;
    private Long memberId;
    private String loginId;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public HotPlaceCommentResponse(Long id, Long hotPlaceId, Long memberId, String loginId, String content, LocalDateTime createdDate) {
        this.id = id;
        this.hotPlaceId = hotPlaceId;
        this.memberId = memberId;
        this.loginId = loginId;
        this.content = content;
        this.createdDate = createdDate;
    }
}

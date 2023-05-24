package com.ssafy.faraway.domain.hotplace.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceSearchCondition {
    private String title;
    private String content;
    private Long memberId;

    @Builder
    public HotPlaceSearchCondition(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
}

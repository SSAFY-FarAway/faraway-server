package com.ssafy.faraway.domain.attraction.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveAttractionLikeDto {
    private Integer contentId;
    private Long memberId;

    @Builder
    public SaveAttractionLikeDto(Integer contentId, Long memberId) {
        this.contentId = contentId;
        this.memberId = memberId;
    }
}

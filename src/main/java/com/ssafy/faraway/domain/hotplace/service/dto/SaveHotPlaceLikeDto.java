package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveHotPlaceLikeDto {
    private Long hotPlaceId;
    private Long memberId;

    @Builder
    public SaveHotPlaceLikeDto(Long hotPlaceId, Long memberId) {
        this.hotPlaceId = hotPlaceId;
        this.memberId = memberId;
    }
}

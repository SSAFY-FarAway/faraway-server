package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveHotPlaceLikeDto {
    private Long hotPlaceId;
    private Long memberId;

    @Builder
    public SaveHotPlaceLikeDto(Long hotPlaceId, Long memberId) {
        this.hotPlaceId = hotPlaceId;
        this.memberId = memberId;
    }
}

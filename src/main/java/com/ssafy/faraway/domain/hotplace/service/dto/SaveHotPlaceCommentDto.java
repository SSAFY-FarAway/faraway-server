package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveHotPlaceCommentDto {
    private Long hotPlaceId;
    private Long memberId;
    private String content;

    @Builder
    public SaveHotPlaceCommentDto(Long hotPlaceId, Long memberId, String content) {
        this.hotPlaceId = hotPlaceId;
        this.memberId = memberId;
        this.content = content;
    }
}

package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateHotPlaceCommentDto {
    private String content;

    @Builder
    public UpdateHotPlaceCommentDto(String content) {
        this.content = content;
    }
}

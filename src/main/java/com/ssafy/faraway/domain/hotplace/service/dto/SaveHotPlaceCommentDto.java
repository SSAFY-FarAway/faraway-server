package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveHotPlaceCommentDto {
    private String content;

    @Builder
    public SaveHotPlaceCommentDto(String content) {
        this.content = content;
    }
}

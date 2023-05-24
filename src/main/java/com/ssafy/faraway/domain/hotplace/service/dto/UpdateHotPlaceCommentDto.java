package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateHotPlaceCommentDto {
    private Long commentId;
    private String content;

    @Builder
    public UpdateHotPlaceCommentDto(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}

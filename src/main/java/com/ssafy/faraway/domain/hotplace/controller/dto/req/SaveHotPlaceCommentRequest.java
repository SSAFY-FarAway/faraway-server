package com.ssafy.faraway.domain.hotplace.controller.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SaveHotPlaceCommentRequest {
    @NotBlank
    private String content;
}

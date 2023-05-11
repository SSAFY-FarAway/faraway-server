package com.ssafy.faraway.domain.hotplace.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateHotPlaceCommentRequest {
    @NotBlank
    private String content;
}

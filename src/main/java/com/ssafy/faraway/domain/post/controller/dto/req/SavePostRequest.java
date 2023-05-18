package com.ssafy.faraway.domain.post.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SavePostRequest {
    @NotBlank
    @Size(max = 120)
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Long categoryId;
}

package com.ssafy.faraway.domain.post.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdatePostRequest {
    @Size(max = 120)
    private String title;
    @NotBlank
    private String content;
}

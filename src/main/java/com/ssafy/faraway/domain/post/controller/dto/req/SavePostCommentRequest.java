package com.ssafy.faraway.domain.post.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SavePostCommentRequest {
    @NotBlank
    private String content;
}
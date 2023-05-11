package com.ssafy.faraway.domain.post.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePostCommentRequest {
    @NotBlank
    private String content;
}

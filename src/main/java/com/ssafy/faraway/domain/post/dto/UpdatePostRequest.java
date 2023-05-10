package com.ssafy.faraway.domain.post.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @Builder
    public UpdatePostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

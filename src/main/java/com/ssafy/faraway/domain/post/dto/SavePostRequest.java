package com.ssafy.faraway.domain.post.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SavePostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Long categoryId;

    @Builder
    public SavePostRequest(String title, String content, Long categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }
}

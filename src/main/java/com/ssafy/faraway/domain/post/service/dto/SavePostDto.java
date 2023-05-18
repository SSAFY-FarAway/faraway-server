package com.ssafy.faraway.domain.post.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SavePostDto {
    private String title;
    private String content;
    private Long categoryId;

    @Builder
    public SavePostDto(String title, String content, Long categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }
}

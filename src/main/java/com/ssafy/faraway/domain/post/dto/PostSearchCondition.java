package com.ssafy.faraway.domain.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PostSearchCondition {
    private String title;
    private String content;

    @Builder
    public PostSearchCondition(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

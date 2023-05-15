package com.ssafy.faraway.domain.post.dto.req;

import lombok.Builder;
import lombok.Data;

@Data
public class PostSearchCondition {
    private String title;
    private String content;
    private Long categoryId;

    @Builder
    public PostSearchCondition(String title, String content, Long categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }
}

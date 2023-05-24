package com.ssafy.faraway.domain.post.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PostSearchCondition {
    private String title;
    private String content;
    private Long categoryId;
    private Long memberId;
    private Integer orderType;

    @Builder
    public PostSearchCondition(String title, String content, Long categoryId, Long memberId, Integer orderType) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.orderType = orderType;
    }
}

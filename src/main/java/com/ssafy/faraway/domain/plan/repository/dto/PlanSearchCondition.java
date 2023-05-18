package com.ssafy.faraway.domain.plan.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PlanSearchCondition {
    private String title;
    private String content;

    @Builder
    public PlanSearchCondition(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

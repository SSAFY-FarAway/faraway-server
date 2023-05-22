package com.ssafy.faraway.domain.plan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePlanCommentDto {
    private String content;

    @Builder
    public UpdatePlanCommentDto(String content) {
        this.content = content;
    }
}

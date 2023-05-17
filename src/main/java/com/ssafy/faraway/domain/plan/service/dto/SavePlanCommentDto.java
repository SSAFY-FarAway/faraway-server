package com.ssafy.faraway.domain.plan.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavePlanCommentDto {
    private String content;

    @Builder
    public SavePlanCommentDto(String content) {
        this.content = content;
    }
}

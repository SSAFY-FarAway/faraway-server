package com.ssafy.faraway.domain.plan.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavePlanCommentDto {
    private Long planId;
    private Long memberId;
    private String content;

    @Builder
    public SavePlanCommentDto(Long planId, Long memberId, String content) {
        this.planId = planId;
        this.memberId = memberId;
        this.content = content;
    }
}

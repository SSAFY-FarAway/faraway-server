package com.ssafy.faraway.domain.plan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SavePlanLikeDto {
    private Long planId;
    private Long memberId;

    @Builder
    public SavePlanLikeDto(Long planId, Long memberId) {
        this.planId = planId;
        this.memberId = memberId;
    }
}

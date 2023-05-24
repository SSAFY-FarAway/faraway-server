package com.ssafy.faraway.domain.plan.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavePlanLikeDto {
    private Long planId;
    private Long memberId;

    @Builder
    public SavePlanLikeDto(Long planId, Long memberId) {
        this.planId = planId;
        this.memberId = memberId;
    }
}

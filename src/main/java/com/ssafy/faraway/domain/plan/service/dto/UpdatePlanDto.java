package com.ssafy.faraway.domain.plan.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePlanDto {
    private Long planId;
    private String title;
    private String content;
    private String travelPlan;

    @Builder
    public UpdatePlanDto(Long planId, String title, String content, String travelPlan) {
        this.planId = planId;
        this.title = title;
        this.content = content;
        this.travelPlan = travelPlan;
    }
}

package com.ssafy.faraway.domain.plan.service.dto;


import lombok.Builder;
import lombok.Data;

@Data
public class SavePlanDto {
    private String title;
    private String content;
    private String tripPlan;

    @Builder
    public SavePlanDto(String title, String content, String tripPlan) {
        this.title = title;
        this.content = content;
        this.tripPlan = tripPlan;
    }
}

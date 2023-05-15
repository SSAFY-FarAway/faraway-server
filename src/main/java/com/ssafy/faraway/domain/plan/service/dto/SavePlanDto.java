package com.ssafy.faraway.domain.plan.service.dto;


import lombok.Builder;
import lombok.Data;

@Data
public class SavePlanDto {
    private String title;
    private String content;
    private String travelPlan;

    @Builder
    public SavePlanDto(String title, String content, String travelPlan) {
        this.title = title;
        this.content = content;
        this.travelPlan = travelPlan;
    }
}

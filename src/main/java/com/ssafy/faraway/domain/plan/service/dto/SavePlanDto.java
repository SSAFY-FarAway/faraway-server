package com.ssafy.faraway.domain.plan.service.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavePlanDto {
    private Long memberId;
    private String title;
    private String content;
    private String travelPlan;

    @Builder
    public SavePlanDto(Long memberId, String title, String content, String travelPlan) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.travelPlan = travelPlan;
    }
}

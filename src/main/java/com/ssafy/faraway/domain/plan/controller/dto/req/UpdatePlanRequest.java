package com.ssafy.faraway.domain.plan.controller.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdatePlanRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String travelPlan;
}

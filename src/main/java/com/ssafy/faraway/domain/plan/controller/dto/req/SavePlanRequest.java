package com.ssafy.faraway.domain.plan.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SavePlanRequest {
    @NotBlank
    @Size(max = 120)
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String travelPlan;
}

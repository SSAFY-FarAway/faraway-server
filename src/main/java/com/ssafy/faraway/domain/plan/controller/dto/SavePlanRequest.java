package com.ssafy.faraway.domain.plan.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SavePlanRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String tripPlan;
}

package com.ssafy.faraway.domain.plan.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SavePlanLikeRequest {
    @NotNull
    private Long planId;
    @NotNull
    private Long memberId;
}

package com.ssafy.faraway.domain.plan.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SavePlanCommentRequest {
    @NotBlank
    private String content;
}

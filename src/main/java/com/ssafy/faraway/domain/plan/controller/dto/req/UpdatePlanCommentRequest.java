package com.ssafy.faraway.domain.plan.controller.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePlanCommentRequest {
    @NotBlank
    private String content;
}

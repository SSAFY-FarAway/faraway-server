package com.ssafy.faraway.domain.plan.controller.dto.res;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanCommentResponse {
    private Long id;
    private Long planId;
    private Long memberId;
    private String loginId;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public PlanCommentResponse(Long id, Long planId, Long memberId, String loginId, String content, LocalDateTime createdDate) {
        this.id = id;
        this.planId = planId;
        this.memberId = memberId;
        this.loginId = loginId;
        this.content = content;
        this.createdDate = createdDate;
    }
}

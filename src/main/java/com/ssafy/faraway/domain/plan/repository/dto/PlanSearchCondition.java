package com.ssafy.faraway.domain.plan.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PlanSearchCondition {
    private String title;
    private String content;
    private Long memberId;
    private Integer orderType;

    @Builder
    public PlanSearchCondition(String title, String content, Long memberId, Integer orderType) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.orderType = orderType;
    }
}

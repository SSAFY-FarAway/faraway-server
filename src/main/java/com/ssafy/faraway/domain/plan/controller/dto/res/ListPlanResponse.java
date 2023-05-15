package com.ssafy.faraway.domain.plan.controller.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ListPlanResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String title;
    private int hit;
    private LocalDateTime createdDate;

    @Builder
    public ListPlanResponse(Long id, Long memberId, String loginId, String title, int hit, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.title = title;
        this.hit = hit;
        this.createdDate = createdDate;
    }
}

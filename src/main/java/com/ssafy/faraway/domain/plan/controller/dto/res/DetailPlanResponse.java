package com.ssafy.faraway.domain.plan.controller.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailPlanResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String title;
    private String content;
    private int hit;
//    private List<Attraction> attractionList;
    private Integer[] shortestPathList;

    @Builder
    public DetailPlanResponse(Long id, Long memberId, String loginId, String title, String content, int hit, Integer[] shortestPathList) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.shortestPathList = shortestPathList;
    }
}

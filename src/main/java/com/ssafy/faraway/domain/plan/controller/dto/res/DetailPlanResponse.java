package com.ssafy.faraway.domain.plan.controller.dto.res;

import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DetailPlanResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String title;
    private String content;
    private int hit;
    private List<AttractionResponse> attractionResponses;
    private List<AttractionResponse> shortestPath = new ArrayList<>();
    private List<PlanCommentResponse> commentResponses;

    @Builder
    public DetailPlanResponse(Long id, Long memberId, String loginId, String title, String content, int hit, List<AttractionResponse> attractionResponses, int[] shortestPath, List<PlanCommentResponse> commentResponses) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.attractionResponses = attractionResponses;
        for (int index : shortestPath) {
            this.shortestPath.add(attractionResponses.get(index));
        }
        this.commentResponses = commentResponses;
    }
}

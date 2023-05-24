package com.ssafy.faraway.domain.plan.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePlanCommentDto {
    private Long commentId;
    private String content;

    @Builder
    public UpdatePlanCommentDto(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}

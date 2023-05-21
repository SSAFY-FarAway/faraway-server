package com.ssafy.faraway.domain.post.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SavePostLikeRequest {
    @NotNull
    private Long postId;
    @NotNull
    private Long memberId;
}

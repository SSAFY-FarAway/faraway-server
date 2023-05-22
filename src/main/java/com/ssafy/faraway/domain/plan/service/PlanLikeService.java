package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.service.dto.SavePlanLikeDto;

public interface PlanLikeService {
    Long save(SavePlanLikeDto dto);
    Long delete(Long likeId);
}

package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.service.dto.SavePlanLikeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlanLikeService {
    Long save(SavePlanLikeDto dto);
    Long delete(Long likeId);
}

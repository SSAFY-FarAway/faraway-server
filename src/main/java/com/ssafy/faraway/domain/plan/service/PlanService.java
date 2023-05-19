package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.controller.dto.req.UpdatePlanRequest;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlanService {
    Long save(SavePlanDto dto, Long memberId);
    Long update(UpdatePlanRequest request, Long planId);
    Long delete(Long planId);
}

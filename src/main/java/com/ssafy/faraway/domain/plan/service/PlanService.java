package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.service.dto.SavePlanDto;
import com.ssafy.faraway.domain.plan.service.dto.UpdatePlanDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlanService {
    Long save(SavePlanDto dto, Long memberId);
    Long update(UpdatePlanDto dto, Long planId);
    Long delete(Long planId);
}

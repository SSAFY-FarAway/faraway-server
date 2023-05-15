package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.service.dto.SavePlanDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlanService {
    Long save(SavePlanDto dto, Long memberId);
}

package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.service.dto.SavePlanCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlanCommentService {
    Long save(Long planId, Long memberId, SavePlanCommentDto dto);
}
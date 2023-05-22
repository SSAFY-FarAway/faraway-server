package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.service.dto.SavePlanCommentDto;
import com.ssafy.faraway.domain.plan.service.dto.UpdatePlanCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlanCommentService {
    Long save(Long planId, Long memberId, SavePlanCommentDto dto);
    Long update(Long commentId, UpdatePlanCommentDto dto);
    Long delete(Long commentId);
}

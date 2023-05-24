package com.ssafy.faraway.domain.plan.repository;

import com.ssafy.faraway.domain.plan.controller.dto.res.PlanResponse;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanQueryRepository {
    Plan searchById(Long planId);

    List<PlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable);

    List<PlanResponse> searchByHit(PlanSearchCondition condition, Pageable pageable);

    List<PlanResponse> searchByLikes(PlanSearchCondition condition, Pageable pageable);

    int getPageTotalCnt(PlanSearchCondition condition);
}

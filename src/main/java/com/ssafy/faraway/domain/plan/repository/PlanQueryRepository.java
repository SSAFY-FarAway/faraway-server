package com.ssafy.faraway.domain.plan.repository;

import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanQueryRepository {
    List<ListPlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable);
    Plan searchById(Long planId);
}

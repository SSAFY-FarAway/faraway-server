package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.controller.dto.res.DetailPlanResponse;
import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PlanQueryService {
    List<ListPlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable);
    DetailPlanResponse searchById(Long planId);
}
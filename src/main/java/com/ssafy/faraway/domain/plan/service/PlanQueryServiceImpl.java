package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
import com.ssafy.faraway.domain.plan.repository.PlanQueryRepository;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanQueryServiceImpl implements PlanQueryService{
    private final PlanQueryRepository planQueryRepository;

    @Override
    public List<ListPlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable) {
        return planQueryRepository.searchByCondition(condition, pageable);
    }
}

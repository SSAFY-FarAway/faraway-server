package com.ssafy.faraway.domain.plan.service.impl;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.plan.controller.dto.req.UpdatePlanRequest;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.repository.PlanRepository;
import com.ssafy.faraway.domain.plan.service.PlanService;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public Long save(SavePlanDto dto, Long memberId) {
        Plan plan = Plan.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .travelPlan(dto.getTravelPlan())
                .member(Member.builder().id(memberId).build())
                .build();
        return planRepository.save(plan).getId();
    }

    @Override
    public Long update(UpdatePlanRequest request, Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        plan.update(request.getTitle(), request.getTitle(), request.getTravelPlan());
        return planId;
    }
}

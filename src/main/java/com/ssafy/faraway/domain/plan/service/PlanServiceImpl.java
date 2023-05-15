package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.repository.PlanRepository;
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
                .tripPlan(dto.getTripPlan())
                .member(Member.builder().id(memberId).build())
                .build();
        return planRepository.save(plan).getId();
    }
}

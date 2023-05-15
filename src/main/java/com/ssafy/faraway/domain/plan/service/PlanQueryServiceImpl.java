package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.plan.controller.dto.res.DetailPlanResponse;
import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.repository.PlanQueryRepository;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanQueryServiceImpl implements PlanQueryService{
    private final PlanQueryRepository planQueryRepository;

    @Override
    public List<ListPlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable) {
        return planQueryRepository.searchByCondition(condition, pageable);
    }

    @Transactional
    @Override
    public DetailPlanResponse searchById(Long planId) {
        Plan plan = planQueryRepository.searchById(planId);

        // 최단경로 계산
//        List<AttractionGetResponseDto> attractionList = attractionService.findAllByIds(plan.getPlan());
//        int[] shortestPath = getShortestPath(attractionList);

        DetailPlanResponse response = DetailPlanResponse.builder()
                .id(plan.getId())
                .memberId(plan.getMember().getId())
                .loginId(plan.getMember().getLoginId())
                .title(plan.getTitle())
                .content(plan.getContent())
                .hit(plan.getHit())
//                .attractionList(attractionList)
//                .shortestPathList(shortestPath)
                .build();
        return null;
    }
}

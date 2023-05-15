package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.common.util.ShortestPath;
import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import com.ssafy.faraway.domain.attraction.repository.AttractionQueryRepository;
import com.ssafy.faraway.domain.plan.controller.dto.res.DetailPlanResponse;
import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.repository.PlanQueryRepository;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanQueryServiceImpl implements PlanQueryService {
    private final PlanQueryRepository planQueryRepository;
    private final AttractionQueryRepository attractionQueryRepository;

    @Override
    public List<ListPlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable) {
        return planQueryRepository.searchByCondition(condition, pageable);
    }

    @Transactional
    @Override
    public DetailPlanResponse searchById(Long planId) {
        Plan plan = planQueryRepository.searchById(planId);
        plan.updateHit();
        // 최단경로 계산
        List<AttractionResponse> attractionResponses = attractionQueryRepository.searchAllByIds(getIds(plan));
        getShortestPath(attractionResponses);

        return DetailPlanResponse.builder()
                .id(plan.getId())
                .memberId(plan.getMember().getId())
                .loginId(plan.getMember().getLoginId())
                .title(plan.getTitle())
                .content(plan.getContent())
                .hit(plan.getHit())
                .attractionResponses(attractionResponses)
                .build();
    }

    private static List<Integer> getIds(Plan plan) {
        List<Integer> ids = new ArrayList<>();
        String[] tripPlan = plan.getTripPlan().split(",");
        for (String s : tripPlan) {
            ids.add(Integer.parseInt(s.trim()));
            log.debug("id: {}", s.trim());
        }
        return ids;
    }

    public void getShortestPath(List<AttractionResponse> responses) {
        double[][] map = makeMap(responses);
        ShortestPath sp = new ShortestPath(map);
        sp.findShortestPath();
        for (AttractionResponse response : responses) {
            log.debug("response: {}", response);
        }
    }

    private double[][] makeMap(List<AttractionResponse> responses) {
        int size = responses.size();
        double[][] map = new double[size][size];
        for (int i = 0; i < size; i++) {
            double x = responses.get(i).getLatitude();
            double y = responses.get(i).getLongitude();
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }
                double nx = responses.get(j).getLatitude();
                double ny = responses.get(j).getLongitude();
                map[i][j] = Math.sqrt(Math.abs(x - nx) * Math.abs(x - nx) + Math.abs(y - ny) * Math.abs(y - ny));
            }
        }
        return map;
    }
}

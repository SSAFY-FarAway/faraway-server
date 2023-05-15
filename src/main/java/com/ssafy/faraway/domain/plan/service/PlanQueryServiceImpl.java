package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.common.util.ShortestPath;
import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import com.ssafy.faraway.domain.attraction.repository.AttractionQueryRepository;
import com.ssafy.faraway.domain.plan.controller.dto.res.DetailPlanResponse;
import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
import com.ssafy.faraway.domain.plan.controller.dto.res.PlanCommentResponse;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.entity.PlanComment;
import com.ssafy.faraway.domain.plan.repository.PlanQueryRepository;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<PlanCommentResponse> commentResponses = getCommentResponses(plan);

        // 최단경로 계산
        List<Integer> ids = getIds(plan);
        List<AttractionResponse> list = attractionQueryRepository.searchAllByIds(ids);
        List<AttractionResponse> attractionResponses = new ArrayList<>();
        for (int id: ids) {
            for (AttractionResponse response : list) {
                if (id == response.getContentId()) {
                    attractionResponses.add(response);
                }
            }
        }
        for (AttractionResponse response : attractionResponses) {
            log.debug("response: {}", response);
        }
        int[] shortestPath = getShortestPath(attractionResponses);

        return DetailPlanResponse.builder()
                .id(plan.getId())
                .memberId(plan.getMember().getId())
                .loginId(plan.getMember().getLoginId())
                .title(plan.getTitle())
                .content(plan.getContent())
                .hit(plan.getHit())
                .attractionResponses(attractionResponses)
                .shortestPath(shortestPath)
                .commentResponses(commentResponses)
                .build();
    }

    private static List<Integer> getIds(Plan plan) {
        List<Integer> ids = new ArrayList<>();
        String[] tripPlan = plan.getTripPlan().split(",");
        for (String s : tripPlan) {
            ids.add(Integer.parseInt(s.trim()));
        }
        return ids;
    }

    public int[] getShortestPath(List<AttractionResponse> responses) {
        double[][] map = makeMap(responses);
        ShortestPath sp = new ShortestPath(map);
        sp.findShortestPath();
        return sp.getResult();
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

    private List<PlanCommentResponse> getCommentResponses(Plan plan) {
        return plan.getPlanComments().stream().map(planComment -> PlanCommentResponse.builder()
                .id(planComment.getId())
                .planId(planComment.getPlan().getId())
                .memberId(planComment.getMember().getId())
                .loginId(planComment.getMember().getLoginId())
                .content(planComment.getContent())
                .createdDate(planComment.getCreatedDate())
                .build()).collect(Collectors.toList());
    }
}

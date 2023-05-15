package com.ssafy.faraway.domain.plan.controller;

import com.ssafy.faraway.domain.plan.controller.dto.SavePlanRequest;
import com.ssafy.faraway.domain.plan.service.PlanService;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
@Api(tags = "plan")
public class PlanRestController {
    private final PlanService planService;

    @PostMapping
    public Long savePlan(@Valid @RequestBody SavePlanRequest request) {
        // TODO: 최영환 2023-05-15 로그인 기능 처리 후 변경해야함
        Long memberId = 1L;
        SavePlanDto dto = SavePlanDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .tripPlan(request.getTripPlan())
                .build();
        return planService.save(dto, memberId);
    }
}

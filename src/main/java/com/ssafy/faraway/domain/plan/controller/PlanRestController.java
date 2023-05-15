package com.ssafy.faraway.domain.plan.controller;

import com.ssafy.faraway.domain.plan.controller.dto.req.SavePlanRequest;
import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import com.ssafy.faraway.domain.plan.service.PlanQueryService;
import com.ssafy.faraway.domain.plan.service.PlanService;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
@Api(tags = "plan")
public class PlanRestController {
    private final PlanService planService;
    private final PlanQueryService planQueryService;

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

    @GetMapping
    public ResultPage<List<ListPlanResponse>> searchPlans(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PlanSearchCondition condition = PlanSearchCondition.builder()
                .title(title)
                .content(content)
                .build();

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<ListPlanResponse> responses = planQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, 10);
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

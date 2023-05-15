package com.ssafy.faraway.domain.plan.controller;

import com.ssafy.faraway.domain.plan.controller.dto.req.SavePlanCommentRequest;
import com.ssafy.faraway.domain.plan.controller.dto.req.SavePlanRequest;
import com.ssafy.faraway.domain.plan.controller.dto.req.UpdatePlanCommentRequest;
import com.ssafy.faraway.domain.plan.controller.dto.req.UpdatePlanRequest;
import com.ssafy.faraway.domain.plan.controller.dto.res.DetailPlanResponse;
import com.ssafy.faraway.domain.plan.controller.dto.res.PlanResponse;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import com.ssafy.faraway.domain.plan.service.PlanCommentService;
import com.ssafy.faraway.domain.plan.service.PlanQueryService;
import com.ssafy.faraway.domain.plan.service.PlanService;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanCommentDto;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
@Api(tags = "plan")
@Slf4j
public class PlanController {
    private final PlanService planService;
    private final PlanQueryService planQueryService;
    private final PlanCommentService planCommentService;

    @PostMapping
    public Long savePlan(@Valid @RequestBody final SavePlanRequest request) {
        // TODO: 최영환 2023-05-15 로그인 기능 처리 후 변경해야함
        Long memberId = 1L;
        SavePlanDto dto = SavePlanDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .travelPlan(request.getTravelPlan())
                .build();
        return planService.save(dto, memberId);
    }

    @GetMapping
    public ResultPage<List<PlanResponse>> searchPlans(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PlanSearchCondition condition = PlanSearchCondition.builder()
                .title(title)
                .content(content)
                .build();

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<PlanResponse> responses = planQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, 10);
    }

    @GetMapping("/{planId}")
    public DetailPlanResponse searchPlan(@PathVariable Long planId) {
        return planQueryService.searchById(planId);
    }

    @PutMapping("{planId}")
    public Long updatePlan(@PathVariable Long planId,
                           @Valid @RequestBody final UpdatePlanRequest request) {
        return planService.update(request, planId);
    }

    @PostMapping("/{planId}/comment")
    public Long savePlanComment(@PathVariable Long planId,
                                @Valid @RequestBody final SavePlanCommentRequest request) {
        // TODO: 최영환 2023-05-15 로그인 기능 처리 후 변경해야함
        Long memberId = 1L;
        SavePlanCommentDto dto = SavePlanCommentDto.builder()
                .content(request.getContent())
                .build();
        return planCommentService.save(planId, memberId, dto);
    }

    @PutMapping("/comment/{commentId}")
    public Long updatePlanComment(@PathVariable Long commentId,
                                  @Valid @RequestBody final UpdatePlanCommentRequest request) {
        return planCommentService.update(commentId, request);
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

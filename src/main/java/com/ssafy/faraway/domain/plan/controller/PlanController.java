package com.ssafy.faraway.domain.plan.controller;

import com.ssafy.faraway.common.domain.ResultPage;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.common.util.SizeConstants;
import com.ssafy.faraway.domain.member.service.JwtService;
import com.ssafy.faraway.domain.plan.controller.dto.req.*;
import com.ssafy.faraway.domain.plan.controller.dto.res.DetailPlanResponse;
import com.ssafy.faraway.domain.plan.controller.dto.res.PlanResponse;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import com.ssafy.faraway.domain.plan.service.PlanCommentService;
import com.ssafy.faraway.domain.plan.service.PlanLikeService;
import com.ssafy.faraway.domain.plan.service.PlanQueryService;
import com.ssafy.faraway.domain.plan.service.PlanService;
import com.ssafy.faraway.domain.plan.service.dto.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssafy.faraway.common.util.SizeConstants.PAGE_SIZE;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
@Api(tags = "plan")
public class PlanController {
    private final PlanService planService;
    private final PlanQueryService planQueryService;
    private final PlanCommentService planCommentService;
    private final PlanLikeService planLikeService;
    private final JwtService jwtService;

    @PostMapping
    public Long savePlan(@Valid @RequestBody final SavePlanRequest request) {
        Long loginId = jwtService.getMemberId();
        SavePlanDto dto = SavePlanDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .travelPlan(request.getTravelPlan())
                .build();
        return planService.save(dto, loginId);
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

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);
        List<PlanResponse> responses = planQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, PAGE_SIZE, planQueryService.getPageTotalCnt(condition));
    }

    @GetMapping("/{planId}")
    public DetailPlanResponse searchPlan(@PathVariable Long planId) {
        Long loginId = jwtService.getMemberId();
        DetailPlanResponse response = planQueryService.searchById(planId, loginId);
        if (response == null) {
            throw new CustomException(ErrorCode.PLAN_NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{planId}")
    public Long updatePlan(@PathVariable Long planId,
                           @Valid @RequestBody final UpdatePlanRequest request) {
        UpdatePlanDto dto = UpdatePlanDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .travelPlan(request.getTravelPlan())
                .build();
        return planService.update(dto, planId);
    }

    @DeleteMapping("/{planId}")
    public Long deletePlan(@PathVariable Long planId) {
        return planService.delete(planId);
    }

    @PostMapping("/{planId}/comment")
    public Long savePlanComment(@PathVariable Long planId,
                                @Valid @RequestBody final SavePlanCommentRequest request) {
        Long loginId = jwtService.getMemberId();
        SavePlanCommentDto dto = SavePlanCommentDto.builder()
                .content(request.getContent())
                .build();
        return planCommentService.save(planId, loginId, dto);
    }

    @PutMapping("/comment/{commentId}")
    public Long updatePlanComment(@PathVariable Long commentId,
                                  @Valid @RequestBody final UpdatePlanCommentRequest request) {
        UpdatePlanCommentDto dto = UpdatePlanCommentDto.builder()
                .content(request.getContent())
                .build();
        return planCommentService.update(commentId, dto);
    }

    @DeleteMapping("/comment/{commentId}")
    public Long deletePlanComment(@PathVariable Long commentId) {
        return planCommentService.delete(commentId);
    }

    @PostMapping("/like")
    public Long savePlanLike(@Valid @RequestBody SavePlanLikeRequest request) {
        SavePlanLikeDto dto = SavePlanLikeDto.builder()
                .planId(request.getPlanId())
                .memberId(request.getMemberId())
                .build();
        return planLikeService.save(dto);
    }

    @DeleteMapping("/like/{likeId}")
    public Long deleteLike(@PathVariable Long likeId) {
        return planLikeService.delete(likeId);
    }
}

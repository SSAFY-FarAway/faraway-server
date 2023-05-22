package com.ssafy.faraway.domain.plan.service.impl;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.entity.PlanLike;
import com.ssafy.faraway.domain.plan.repository.PlanLikeRepository;
import com.ssafy.faraway.domain.plan.service.PlanLikeService;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanLikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanLikeServiceImpl implements PlanLikeService {
    private final PlanLikeRepository planLikeRepository;

    @Override
    public Long save(SavePlanLikeDto dto) {
        PlanLike like = PlanLike.builder()
                .plan(Plan.builder().id(dto.getPlanId()).build())
                .member(Member.builder().id(dto.getMemberId()).build())
                .build();
        return planLikeRepository.save(like).getId();
    }

    @Override
    public Long delete(Long likeId) {
        PlanLike like = planLikeRepository.findById(likeId)
                .orElseThrow(() -> new CustomException(ErrorCode.PLAN_NOT_FOUND));
        planLikeRepository.delete(like);
        return likeId;
    }
}

package com.ssafy.faraway.domain.plan.service.impl;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.entity.PlanComment;
import com.ssafy.faraway.domain.plan.repository.PlanCommentRepository;
import com.ssafy.faraway.domain.plan.service.PlanCommentService;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanCommentDto;
import com.ssafy.faraway.domain.plan.service.dto.UpdatePlanCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanCommentServiceImpl implements PlanCommentService {
    private final PlanCommentRepository planCommentRepository;

    @Override
    public Long save(SavePlanCommentDto dto) {
        PlanComment planComment = PlanComment.builder()
                .content(dto.getContent())
                .member(Member.builder().id(dto.getMemberId()).build())
                .plan(Plan.builder().id(dto.getPlanId()).build())
                .build();
        return planCommentRepository.save(planComment).getId();
    }

    @Override
    public Long update(UpdatePlanCommentDto dto) {
        PlanComment planComment = planCommentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        planComment.update(dto.getContent());
        return planComment.getId();
    }

    @Override
    public Long delete(Long commentId) {
        PlanComment planComment = planCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        planCommentRepository.delete(planComment);
        return commentId;
    }
}

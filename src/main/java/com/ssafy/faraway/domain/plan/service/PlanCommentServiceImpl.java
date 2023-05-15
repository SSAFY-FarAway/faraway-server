package com.ssafy.faraway.domain.plan.service;

import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.plan.controller.dto.req.UpdatePlanCommentRequest;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.entity.PlanComment;
import com.ssafy.faraway.domain.plan.repository.PlanCommentRepository;
import com.ssafy.faraway.domain.plan.service.dto.SavePlanCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlanCommentServiceImpl implements PlanCommentService {
    private final PlanCommentRepository planCommentRepository;

    @Override
    public Long save(Long planId, Long memberId, SavePlanCommentDto dto) {
        PlanComment planComment = PlanComment.builder()
                .content(dto.getContent())
                .member(Member.builder().id(memberId).build())
                .plan(Plan.builder().id(planId).build())
                .build();
        return planCommentRepository.save(planComment).getId();
    }

    @Override
    public Long update(Long commentId, UpdatePlanCommentRequest request) {
        PlanComment planComment = planCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        planComment.update(request.getContent());
        return commentId;
    }
}

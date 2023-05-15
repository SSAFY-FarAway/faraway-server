package com.ssafy.faraway.domain.plan.repository;

import com.ssafy.faraway.domain.plan.entity.PlanComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCommentRepository extends JpaRepository<PlanComment, Long> {
}

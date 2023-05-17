package com.ssafy.faraway.domain.plan.repository;

import com.ssafy.faraway.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}

package com.ssafy.faraway.domain.plan.repository;

public interface PlanLikeQueryRepository {
    Long searchLikeId(Long planId, Long memberId);
}

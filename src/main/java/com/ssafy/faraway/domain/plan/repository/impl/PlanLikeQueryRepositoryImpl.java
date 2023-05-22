package com.ssafy.faraway.domain.plan.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.member.entity.QMember;
import com.ssafy.faraway.domain.plan.entity.QPlan;
import com.ssafy.faraway.domain.plan.entity.QPlanLike;
import com.ssafy.faraway.domain.plan.repository.PlanLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static com.ssafy.faraway.domain.plan.entity.QPlan.plan;
import static com.ssafy.faraway.domain.plan.entity.QPlanLike.planLike;

@Repository
@RequiredArgsConstructor
public class PlanLikeQueryRepositoryImpl implements PlanLikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long searchLikeId(Long planId, Long memberId) {
        return queryFactory
                .select(planLike.id)
                .from(planLike)
                .join(planLike.plan, plan)
                .join(planLike.member, member)
                .where(
                        isPlan(planId),
                        isMember(memberId)
                )
                .fetchOne();
    }

    private static BooleanExpression isMember(Long memberId) {
        return planLike.member.id.eq(memberId);
    }

    private static BooleanExpression isPlan(Long planId) {
        return planLike.plan.id.eq(planId);
    }
}

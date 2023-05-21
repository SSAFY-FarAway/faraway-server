package com.ssafy.faraway.domain.plan.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.plan.controller.dto.res.PlanResponse;
import com.ssafy.faraway.domain.plan.entity.Plan;
import com.ssafy.faraway.domain.plan.repository.PlanQueryRepository;
import com.ssafy.faraway.domain.plan.repository.dto.PlanSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static com.ssafy.faraway.domain.plan.entity.QPlan.plan;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class PlanQueryRepositoryImpl implements PlanQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable) {
        List<Long> ids = getIds(condition, pageable);

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.fields(PlanResponse.class,
                        plan.id,
                        plan.member.id.as("memberId"),
                        plan.member.loginId,
                        plan.title,
                        plan.hit,
                        plan.createdDate
                ))
                .from(plan)
                .join(plan.member, member)
                .where(plan.id.in(ids))
                .orderBy(plan.createdDate.desc())
                .fetch();
    }

    @Override
    public Plan searchById(Long planId) {
        return queryFactory
                .select(plan)
                .from(plan)
                .join(plan.member, member).fetchJoin()
                .where(plan.id.eq(planId))
                .fetchOne();
    }

    @Override
    public int getPageTotalCnt(PlanSearchCondition condition) {
        return queryFactory
                .select(plan.count())
                .from(plan)
                .join(plan.member, member)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                ).fetchFirst().intValue();
    }

    private List<Long> getIds(PlanSearchCondition condition, Pageable pageable) {
        return queryFactory
                .select(plan.id)
                .from(plan)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                )
                .orderBy(plan.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? plan.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isContent(String content) {
        return hasText(content) ? plan.content.like("%" + content + "%") : null;
    }
}
package com.ssafy.faraway.domain.plan.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.plan.controller.dto.res.ListPlanResponse;
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
    public List<ListPlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
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

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.fields(ListPlanResponse.class,
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

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? plan.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isContent(String content) {
        return hasText(content) ? plan.content.like("%" + content + "%") : null;
    }
}
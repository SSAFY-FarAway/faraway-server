package com.ssafy.faraway.domain.hotplace.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceQueryRepository;
import com.ssafy.faraway.domain.hotplace.repository.dto.HotPlaceSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.hotplace.entity.QHotPlace.hotPlace;
import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class HotPlaceQueryRepositoryImpl implements HotPlaceQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public HotPlace searchById(Long hotPlaceId) {
        return queryFactory
                .select(hotPlace)
                .from(hotPlace)
                .join(hotPlace.member, member).fetchJoin()
                .where(hotPlace.id.eq(hotPlaceId))
                .fetchOne();
    }

    @Override
    public List<HotPlace> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(hotPlace.id)
                .from(hotPlace)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                )
                .orderBy(hotPlace.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory.select(hotPlace)
                .from(hotPlace)
                .join(hotPlace.member, member).fetchJoin()
                .where(hotPlace.id.in(ids))
                .orderBy(hotPlace.createdDate.desc())
                .fetch();
    }

    @Override
    public List<HotPlace> searchByHit(HotPlaceSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(hotPlace.id)
                .from(hotPlace)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                )
                .orderBy(hotPlace.hit.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory.select(hotPlace)
                .from(hotPlace)
                .join(hotPlace.member, member).fetchJoin()
                .where(hotPlace.id.in(ids))
                .orderBy(hotPlace.hit.desc())
                .fetch();
    }

    @Override
    public List<HotPlace> searchByLikeCnt(HotPlaceSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(hotPlace.id)
                .from(hotPlace)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                )
                .orderBy(hotPlace.likes.size().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory.select(hotPlace)
                .from(hotPlace)
                .join(hotPlace.member, member).fetchJoin()
                .where(hotPlace.id.in(ids))
                .orderBy(hotPlace.likes.size().desc())
                .fetch();
    }

    @Override
    public int getPageTotalCnt(HotPlaceSearchCondition condition) {
        return queryFactory
                .select(hotPlace.count())
                .from(hotPlace)
                .join(hotPlace.member, member)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                ).fetchFirst().intValue();
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? hotPlace.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isContent(String content) {
        return hasText(content) ? hotPlace.content.like("%" + content + "%") : null;
    }
}

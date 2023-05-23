package com.ssafy.faraway.domain.attraction.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.attraction.repository.AttractionLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ssafy.faraway.domain.attraction.entity.QAttractionInfo.attractionInfo;
import static com.ssafy.faraway.domain.attraction.entity.QAttractionLike.attractionLike;
import static com.ssafy.faraway.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class AttractionLikeQueryRepositoryImpl implements AttractionLikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long searchLikeId(Long memberId, Integer contentId) {
        return queryFactory
                .select(attractionLike.id)
                .from(attractionLike)
                .join(attractionLike.attractionInfo, attractionInfo)
                .join(attractionLike.member ,member)
                .where(
                        isAttraction(contentId),
                        isMember(memberId)
                ).fetchOne();
    }

    private BooleanExpression isAttraction(Integer contentId) {
        return attractionLike.attractionInfo.contentId.eq(contentId);
    }

    BooleanExpression isMember(Long memberId) {
        if (memberId == null) {
            return null;
        }
        return attractionLike.member.id.eq(memberId);
    }
}

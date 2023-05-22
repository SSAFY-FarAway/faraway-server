package com.ssafy.faraway.domain.hotplace.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ssafy.faraway.domain.hotplace.entity.QHotPlace.hotPlace;
import static com.ssafy.faraway.domain.hotplace.entity.QHotPlaceLike.hotPlaceLike;
import static com.ssafy.faraway.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class HotPlaceLikeQueryRepositoryImpl implements HotPlaceLikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long searchLikeId(Long memberId, Long hotPlaceId) {
        return queryFactory
                .select(hotPlaceLike.id)
                .from(hotPlaceLike)
                .join(hotPlaceLike.hotPlace, hotPlace)
                .join(hotPlaceLike.member, member)
                .where(
                        isMember(memberId),
                        isHotPlace(hotPlaceId)
                )
                .fetchOne();
    }

    private BooleanExpression isMember(Long memberId) {
        return hotPlaceLike.member.id.eq(memberId);
    }

    private BooleanExpression isHotPlace(Long hotPlaceId) {
        return hotPlaceLike.hotPlace.id.eq(hotPlaceId);
    }
}

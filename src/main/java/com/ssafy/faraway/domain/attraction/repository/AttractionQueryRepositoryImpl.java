package com.ssafy.faraway.domain.attraction.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.attraction.entity.QAttractionDesc.attractionDesc;
import static com.ssafy.faraway.domain.attraction.entity.QAttractionInfo.attractionInfo;
import static com.ssafy.faraway.domain.attraction.entity.QGugun.gugun;
import static com.ssafy.faraway.domain.attraction.entity.QSido.sido;

@Repository
@RequiredArgsConstructor
public class AttractionQueryRepositoryImpl implements AttractionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition, Pageable pageable) {
        List<Integer> ids = getIds(condition, pageable);

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.fields(AttractionResponse.class,
                        attractionInfo.contentId,
                        attractionInfo.title,
                        attractionInfo.addr1,
                        attractionInfo.addr2,
                        attractionInfo.zipcode,
                        attractionInfo.tel,
                        attractionInfo.firstImage,
                        attractionInfo.latitude,
                        attractionInfo.longitude,
                        attractionDesc.overview
                ))
                .from(attractionInfo)
                .join(attractionInfo.attractionDesc, attractionDesc)
                .where(attractionInfo.contentId.in(ids))
                .fetch();
    }

    @Override
    public List<AttractionResponse> SearchByIds(List<Integer> ids) {
        return queryFactory
                .select(Projections.fields(AttractionResponse.class,
                        attractionInfo.contentId,
                        attractionInfo.title,
                        attractionInfo.addr1,
                        attractionInfo.addr2,
                        attractionInfo.zipcode,
                        attractionInfo.tel,
                        attractionInfo.firstImage,
                        attractionInfo.latitude,
                        attractionInfo.longitude))
                .from(attractionInfo)
                .where(attractionInfo.contentId.in(ids))
                .fetch();
    }

    @Override
    public List<GugunResponse> searchGugunBySidoCode(Integer sidoCode) {
        return queryFactory
                .select(Projections.fields(GugunResponse.class,
                        gugun.gugunCode,
                        gugun.gugunName))
                .from(gugun)
                .join(gugun.sido, sido)
                .where(gugun.sido.sidoCode.eq(sidoCode))
                .fetch();
    }

    @Override
    public int getPageTotalCnt(AttractionSearchCondition condition) {
        return queryFactory
                .select(attractionInfo.count())
                .from(attractionInfo)
                .where(
                        isSidoCode(condition.getSidoCode()),
                        isGugunCode(condition.getGugunCode()),
                        isContentTypeId(condition.getContentTypeId())
                ).fetchFirst().intValue();
    }

    private List<Integer> getIds(AttractionSearchCondition condition, Pageable pageable) {
        return queryFactory
                .select(attractionInfo.contentId)
                .from(attractionInfo)
                .join(attractionInfo.attractionDesc, attractionDesc)
                .where(
                        isSidoCode(condition.getSidoCode()),
                        isGugunCode(condition.getGugunCode()),
                        isContentTypeId(condition.getContentTypeId())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private boolean hasCondition(Integer condition) {
        return condition != null;
    }

    private BooleanExpression isSidoCode(Integer sidoCode) {
        return hasCondition(sidoCode) ? attractionInfo.sido.sidoCode.eq(sidoCode) : null;
    }

    private BooleanExpression isGugunCode(Integer gugunCode) {
        return hasCondition(gugunCode) ? attractionInfo.gugun.gugunCode.eq(gugunCode) : null;
    }

    private BooleanExpression isContentTypeId(Integer ContentTypeId) {
        return hasCondition(ContentTypeId) ? attractionInfo.contentTypeId.eq(ContentTypeId) : null;
    }
}

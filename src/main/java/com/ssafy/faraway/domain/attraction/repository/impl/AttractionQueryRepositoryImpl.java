package com.ssafy.faraway.domain.attraction.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import com.ssafy.faraway.domain.attraction.entity.AttractionInfo;
import com.ssafy.faraway.domain.attraction.repository.AttractionQueryRepository;
import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.attraction.entity.QAttractionDesc.attractionDesc;
import static com.ssafy.faraway.domain.attraction.entity.QAttractionInfo.attractionInfo;
import static com.ssafy.faraway.domain.attraction.entity.QAttractionLike.attractionLike;
import static com.ssafy.faraway.domain.attraction.entity.QGugun.gugun;
import static com.ssafy.faraway.domain.attraction.entity.QSido.sido;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AttractionQueryRepositoryImpl implements AttractionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AttractionInfo> searchByCondition(AttractionSearchCondition condition, Long memberId, Pageable pageable) {
        List<Integer> ids = getIds(condition, pageable);

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        for (Integer id : ids) {
            log.debug("id: {}", id);
        }

        return queryFactory
                .select(attractionInfo)
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
                        attractionInfo.longitude
                ))
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
                        isContentTypeId(condition.getContentTypeId()),
                        isTitle(condition.getTitle()),
                        isAddress(condition.getAddress())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression isSidoCode(Integer sidoCode) {
        if (sidoCode == null) {
            return null;
        }
        return attractionInfo.sido.sidoCode.eq(sidoCode);
    }

    private BooleanExpression isGugunCode(Integer gugunCode) {
        if (gugunCode == null) {
            return null;
        }
        return attractionInfo.gugun.gugunCode.eq(gugunCode);
    }

    private BooleanExpression isContentTypeId(Integer contentTypeId) {
        if (contentTypeId == null) {
            return null;
        }
        return attractionInfo.contentTypeId.eq(contentTypeId);
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? attractionInfo.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isAddress(String address) {
        return hasText(address) ? attractionInfo.addr1.like("%" + address + "%") : null;
    }
}

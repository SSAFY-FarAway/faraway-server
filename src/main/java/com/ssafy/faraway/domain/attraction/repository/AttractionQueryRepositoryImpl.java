package com.ssafy.faraway.domain.attraction.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.attraction.dto.req.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import com.ssafy.faraway.domain.attraction.dto.res.GugunResponse;
import com.ssafy.faraway.domain.attraction.dto.res.SidoResponse;
import com.ssafy.faraway.domain.attraction.entity.Sido;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.ssafy.faraway.domain.attraction.entity.QAttractionInfo.attractionInfo;
import static com.ssafy.faraway.domain.attraction.entity.QGugun.gugun;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class AttractionQueryRepositoryImpl implements AttractionQueryRepository{
    private final JPAQueryFactory queryFactory;
    /*
    return queryFactory
                .select(post)
                .from(post)
                .join(post.member, member).fetchJoin()
                .join(post.category, category).fetchJoin()
                .where(post.id.eq(postId))
                .fetchOne();
     */
    @Override
    public List<AttractionResponse> searchAll() {
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
                .fetch();
    }

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition) {
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
                .where(isSidoCode(condition.getSidoCode()),
                        isGugunCode(condition.getGugunCode()),
                        isContentTypeId(condition.getContentTypeId())
                )
                .fetch();
    }

    @Override
    public List<AttractionResponse> searchAllByIds(List<Integer> ids) {
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
                .where(gugun.sido.sidoCode.eq(sidoCode))
                .fetch();
    }


    private boolean hasCondition(Integer condition){
        if(condition == null){
            return false;
        }
        return true;
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

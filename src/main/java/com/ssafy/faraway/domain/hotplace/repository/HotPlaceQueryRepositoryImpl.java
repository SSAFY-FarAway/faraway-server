package com.ssafy.faraway.domain.hotplace.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.hotplace.dto.req.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.dto.res.ListHotPlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.hotplace.entity.QHotPlace.hotPlace;
import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static com.ssafy.faraway.domain.post.entity.QPost.post;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class HotPlaceQueryRepositoryImpl implements HotPlaceQueryRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ListHotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
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

        return queryFactory
                .select(Projections.fields(ListHotPlaceResponse.class,
                        hotPlace.id,
                        hotPlace.member.id.as("memberId"),
                        hotPlace.member.loginId,
                        hotPlace.title,
                        hotPlace.content,
                        hotPlace.hit,
                        hotPlace.address.mainAddress,
                        hotPlace.address.subAddress,
                        hotPlace.rating,
                        hotPlace.createdDate))
                .from(hotPlace)
                .join(hotPlace.member, member)
                .orderBy(hotPlace.createdDate.desc())
                .fetch();
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? post.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isContent(String content) {
        return hasText(content) ? post.content.like("%" + content + "%") : null;
    }
}

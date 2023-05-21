package com.ssafy.faraway.domain.post.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.post.repository.PostQueryRepository;
import com.ssafy.faraway.domain.post.repository.dto.PostSearchCondition;
import com.ssafy.faraway.domain.post.controller.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static com.ssafy.faraway.domain.post.entity.QCategory.category;
import static com.ssafy.faraway.domain.post.entity.QPost.post;
import static org.springframework.util.StringUtils.hasText;


@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Post searchById(Long postId) {
        return queryFactory
                .select(post)
                .from(post)
                .join(post.member, member).fetchJoin()
                .join(post.category, category).fetchJoin()
                .where(post.id.eq(postId))
                .fetchOne();
    }

    @Override
    public List<PostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable) {
        List<Long> ids = getIds(condition, pageable);

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.fields(PostResponse.class,
                        post.id,
                        post.member.id.as("memberId"),
                        post.member.loginId,
                        post.category.categoryName,
                        post.title,
                        post.hit,
                        post.likes.size().as("likeCnt"),
                        post.createdDate
                ))
                .from(post)
                .join(post.member, member)
                .join(post.category, category)
                .where(post.id.in(ids))
                .orderBy(post.createdDate.desc())
                .fetch();
    }

    @Override
    public int getPageTotalCnt(PostSearchCondition condition) {
        return queryFactory
                .select(post.count())
                .from(post)
                .join(post.member, member)
                .join(post.category, category)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent()),
                        isCategory(condition.getCategoryId())
                ).fetchFirst().intValue();
    }

    private List<Long> getIds(PostSearchCondition condition, Pageable pageable) {
        return queryFactory
                .select(post.id)
                .from(post)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent()),
                        isCategory(condition.getCategoryId())
                )
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? post.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isContent(String content) {
        return hasText(content) ? post.content.like("%" + content + "%") : null;
    }

    private BooleanExpression isCategory(Long categoryId) {
        return category.id.eq(categoryId);
    }
}

package com.ssafy.faraway.domain.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.post.dto.ListPostResponse;
import com.ssafy.faraway.domain.post.dto.PostSearchCondition;
import com.ssafy.faraway.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static com.ssafy.faraway.domain.post.entity.QCategory.category;
import static com.ssafy.faraway.domain.post.entity.QPost.post;
import static org.springframework.util.StringUtils.hasText;


@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
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
    public List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(post.id)
                .from(post)
                .where(
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                )
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.fields(ListPostResponse.class,
                        post.id,
                        post.member.id.as("memberId"),
                        post.member.loginId,
                        post.category.categoryName,
                        post.title,
                        post.hit,
                        post.createdDate
                        ))
                .from(post)
                .join(post.member, member)
                .join(post.category, category)
                .where(post.id.in(ids))
                .orderBy(post.createdDate.desc())
                .fetch();
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? post.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isContent(String content) {
        return hasText(content) ? post.content.like("%" + content + "%") : null;
    }
}

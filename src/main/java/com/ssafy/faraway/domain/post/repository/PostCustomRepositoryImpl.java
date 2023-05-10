package com.ssafy.faraway.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;

import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static com.ssafy.faraway.domain.post.entity.QCategory.category;
import static com.ssafy.faraway.domain.post.entity.QPost.post;


@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository{
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
}

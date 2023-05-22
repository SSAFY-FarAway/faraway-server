package com.ssafy.faraway.domain.post.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.post.repository.PostLikeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ssafy.faraway.domain.member.entity.QMember.member;
import static com.ssafy.faraway.domain.post.entity.QPost.post;
import static com.ssafy.faraway.domain.post.entity.QPostLike.postLike;

@Repository
@RequiredArgsConstructor
public class PostLikeQueryRepositoryImpl implements PostLikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long searchLikeId(Long memberId, Long postId) {
        return queryFactory
                .select(postLike.id)
                .from(postLike)
                .join(postLike.post, post)
                .join(postLike.member, member)
                .where(
                        isPost(postId),
                        isMember(memberId)
                )
                .fetchOne();
    }

    BooleanExpression isMember(Long memberId) {
        return postLike.member.id.eq(memberId);
    }

    BooleanExpression isPost(Long postId) {
        return postLike.post.id.eq(postId);
    }
}

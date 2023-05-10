package com.ssafy.faraway.domain.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.post.entity.Category;
import lombok.RequiredArgsConstructor;

import static com.ssafy.faraway.domain.post.entity.QCategory.category;


@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository{
    private final JPAQueryFactory queryFactory;
}

package com.ssafy.faraway.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.member.dto.req.LoginEncMember;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ssafy.faraway.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public LoginMemberResponse login(LoginEncMember dto) {
        return queryFactory
                .select(Projections.fields(LoginMemberResponse.class,
                        member.id,
                        member.loginId,
                        member.name.lastName,
                        member.name.firstName,
                        member.mileage,
                        member.role
                ))
                .from(member)
                .where(member.loginId.eq(dto.getLoginId())
                .and(member.loginPwd.eq(dto.getLoginPwd())))
                .fetchOne();
    }

    @Override
    public Long SearchIdByLoginId(String loginId) {
        return queryFactory
                .select(member.id)
                .from(member)
                .where(member.loginId.eq(loginId))
                .fetchOne();
    }

    @Override
    public String SearchSaltById(Long id) {
        return queryFactory
                .select(member.salt)
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
    }
}

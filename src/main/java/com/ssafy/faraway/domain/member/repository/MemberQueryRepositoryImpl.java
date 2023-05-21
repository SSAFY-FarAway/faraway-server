package com.ssafy.faraway.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.faraway.domain.member.controller.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.repository.dto.LoginEncMember;
import com.ssafy.faraway.domain.member.controller.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.service.dto.FindLoginIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public Member searchById(Long memberId) {
        return queryFactory
                .select(member)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
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
    public List<ListMemberResponse> searchAll(Pageable pageable) {
        List<Long> ids = queryFactory
                .select(member.id)
                .from(member)
                .orderBy(member.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if(CollectionUtils.isEmpty(ids)){
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.fields(ListMemberResponse.class,
                        member.id,
                        member.loginId,
                        member.name.lastName,
                        member.name.firstName,
                        member.address.mainAddress,
                        member.birth,
                        member.email,
                        member.mileage,
                        member.role,
                        member.certified
                ))
                .from(member)
                .orderBy(member.createdDate.desc())
                .fetch();
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
    public String SearchSaltById(Long memberId) {
        return queryFactory
                .select(member.salt)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public String SearchLoginIdByEmailAndBirth(FindLoginIdDto dto) {
        return queryFactory
                .select(member.loginId)
                .from(member)
                .where(member.birth.eq(dto.getBirth())
                        .and(member.email.eq(dto.getEmail())))
                .fetchOne();
    }

    @Override
    public String SearchLoginPwdById(Long memberId) {
        return queryFactory
                .select(member.loginPwd)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public String searchRefreshToken(Long memberId) {
        return queryFactory
                .select(member.token)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public LoginMemberResponse login(Long memberId) {
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
                .where(member.id.eq(memberId))
                .fetchOne();
    }


}

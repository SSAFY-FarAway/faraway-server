package com.ssafy.faraway.domain.member.repository;

import com.ssafy.faraway.domain.member.dto.req.LoginEncMember;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;

public interface MemberQueryRepository {
    LoginMemberResponse login(LoginEncMember dto);
    Long SearchIdByLoginId(String loginId);
    String SearchSaltById(Long id);
}

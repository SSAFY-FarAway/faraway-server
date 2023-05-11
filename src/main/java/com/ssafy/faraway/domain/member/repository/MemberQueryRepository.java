package com.ssafy.faraway.domain.member.repository;

import com.ssafy.faraway.domain.member.dto.req.LoginEncMember;
import com.ssafy.faraway.domain.member.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberQueryRepository {
    LoginMemberResponse login(LoginEncMember dto);
    Member searchById(Long memberId);
    List<ListMemberResponse> searchAll(Pageable pageable);
    Long SearchIdByLoginId(String loginId);
    String SearchSaltById(Long id);
}

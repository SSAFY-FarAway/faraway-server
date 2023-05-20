package com.ssafy.faraway.domain.member.repository;

import com.ssafy.faraway.domain.member.controller.dto.req.FindLoginIdRequest;
import com.ssafy.faraway.domain.member.repository.dto.LoginEncMember;
import com.ssafy.faraway.domain.member.controller.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.controller.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberQueryRepository {
    LoginMemberResponse login(LoginEncMember dto);
    LoginMemberResponse login(Long memberId);
    Member searchById(Long memberId);
    List<ListMemberResponse> searchAll(Pageable pageable);
    Long SearchIdByLoginId(String loginId);
    String SearchSaltById(Long memberId);
    String SearchLoginIdByEmailAndBirth(FindLoginIdRequest request);
    String SearchLoginPwdById(Long memberId);
    String searchRefreshToken(Long memberId);




}

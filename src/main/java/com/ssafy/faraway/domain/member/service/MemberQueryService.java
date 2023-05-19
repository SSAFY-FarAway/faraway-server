package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.dto.req.CheckLoginPwdRequest;
import com.ssafy.faraway.domain.member.dto.req.FindLoginIdRequest;
import com.ssafy.faraway.domain.member.dto.req.LoginMemberRequest;
import com.ssafy.faraway.domain.member.dto.req.UpdateLoginPwdRequest;
import com.ssafy.faraway.domain.member.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.MemberResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberQueryService {
    MemberResponse searchById(Long memberId);
    List<ListMemberResponse> searchAll(Pageable pageable);
    LoginMemberResponse login(LoginMemberRequest request);
    String searchLoginId(FindLoginIdRequest request);
    boolean checkLoginPwd(CheckLoginPwdRequest request);
    String searchRefreshToken(Long memberId);
    LoginMemberResponse searchLoginMemberById(Long memberId);

}

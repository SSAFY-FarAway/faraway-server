package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.controller.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.controller.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.controller.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.service.dto.CheckLoginPwdDto;
import com.ssafy.faraway.domain.member.service.dto.FindLoginIdDto;
import com.ssafy.faraway.domain.member.service.dto.LoginMemberDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberQueryService {
    MemberResponse searchById(Long memberId);
    List<ListMemberResponse> searchAll(Pageable pageable);
    LoginMemberResponse login(LoginMemberDto dto);
    String searchLoginId(FindLoginIdDto dto);
    boolean checkLoginPwd(CheckLoginPwdDto dto);
    String searchRefreshToken(Long memberId);
    LoginMemberResponse searchLoginMemberById(Long memberId);

}

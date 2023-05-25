package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.controller.dto.req.*;
import com.ssafy.faraway.domain.member.service.dto.*;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long saveMember(SaveMemberDto dto);
    boolean checkLoginId(String loginId);
    boolean checkEmail(String email);
    Long updateLoginPwd(UpdateLoginPwdDto dto);
    Long updateMember(UpdateMemberDto dto);
    Long resetLoginPwd(ResetLoginPwdDto dto);
    Long deleteMember(DeleteMemberDto dto);
    Long saveRefreshToken(Long id, String refreshToken);
    Long deleteRefreshToken(Long id);

}

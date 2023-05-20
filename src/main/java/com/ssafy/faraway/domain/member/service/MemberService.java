package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.controller.dto.req.*;
import com.ssafy.faraway.domain.member.service.dto.SaveMemberDto;
import com.ssafy.faraway.domain.member.service.dto.UpdateLoginPwdDto;
import com.ssafy.faraway.domain.member.service.dto.UpdateMemberDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long saveMember(SaveMemberDto dto);
    boolean checkLoginId(String loginId);
    Long updateLoginPwd(UpdateLoginPwdDto dto);
    Long updateMember(UpdateMemberDto dto);
    Long resetLoginPwd(ResetLoginPwdRequest request);
    Long deleteMember(DeleteMemberRequest request);
    Long saveRefreshToken(Long id, String refreshToken);
    Long deleteRefreshToken(Long id);

}

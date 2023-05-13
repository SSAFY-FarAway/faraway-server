package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.dto.req.*;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import org.hibernate.sql.Update;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long saveMember(SaveMemberRequest dto);
    boolean checkLoginId(String loginId);
    Long updateLoginPwd(UpdateLoginPwdRequest request);
    Long updateMember(UpdateMemberRequest request);
    Long resetLoginPwd(ResetLoginPwdRequest request);
}

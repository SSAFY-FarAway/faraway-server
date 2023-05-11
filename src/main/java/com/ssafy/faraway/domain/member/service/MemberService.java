package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.dto.req.SaveMemberRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long saveMember(SaveMemberRequest dto);
}

package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.dto.SaveMemberRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {
    Long saveMember(SaveMemberRequest dto);
}

package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.MemberResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberQueryService {
    MemberResponse searchById(Long memberId);
    List<ListMemberResponse> searchAll(Pageable pageable);
}

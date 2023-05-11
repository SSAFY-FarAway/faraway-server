package com.ssafy.faraway.domain.member.repository;

import com.ssafy.faraway.domain.member.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberQueryRepository {
    Member searchById(Long memberId);
    List<ListMemberResponse> searchAll(Pageable pageable);
}

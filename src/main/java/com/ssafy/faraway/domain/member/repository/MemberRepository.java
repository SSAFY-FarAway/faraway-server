package com.ssafy.faraway.domain.member.repository;

import com.ssafy.faraway.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

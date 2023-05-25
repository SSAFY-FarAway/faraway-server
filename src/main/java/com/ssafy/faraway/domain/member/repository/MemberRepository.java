package com.ssafy.faraway.domain.member.repository;

import com.ssafy.faraway.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    Optional<Member> findByEmail(@Param("email") String email);

    Optional<Member> findById(@Param("id")String id);

}

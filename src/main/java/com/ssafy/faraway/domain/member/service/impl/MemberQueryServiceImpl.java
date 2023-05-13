package com.ssafy.faraway.domain.member.service.impl;

import com.ssafy.faraway.common.util.Encrypt;
import com.ssafy.faraway.domain.member.dto.req.*;
import com.ssafy.faraway.domain.member.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import com.ssafy.faraway.domain.member.repository.MemberQueryRepository;
import com.ssafy.faraway.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberQueryRepository memberQueryRepository;

    // 회원 상세조회
    public MemberResponse searchById(Long memberId) {
        Member member = memberQueryRepository.searchById(memberId);
        return MemberResponse.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .birth(member.getBirth())
                .zipcode(member.getAddress().getZipcode())
                .mainAddress(member.getAddress().getMainAddress())
                .subAddress(member.getAddress().getSubAddress())
                .mileage(member.getMileage())
                .role(member.getRole())
                .certified(member.getCertified())
                .build();
    }

    // 회원 전체조회
    @Override
    public List<ListMemberResponse> searchAll(Pageable pageable) {
        // TODO : 관리자 계정 여부 확인 필요.
        return memberQueryRepository.searchAll(pageable);
    }
    @Override
    public LoginMemberResponse login(LoginMemberRequest request) {
        Long id = memberQueryRepository.SearchIdByLoginId(request.getLoginId());
        String salt = memberQueryRepository.SearchSaltById(id);
        String encLoginPwd = Encrypt.encrypt(request.getLoginPwd(), salt);
        System.out.println(salt);


        LoginEncMember dto = LoginEncMember.builder()
                .loginId(request.getLoginId())
                .loginPwd(encLoginPwd)
                .build();
        return memberQueryRepository.login(dto);
    }

    @Override
    public String searchLoginId(FindLoginIdRequest request) {
        return memberQueryRepository.SearchLoginIdByEmailAndBirth(request);
    }


}

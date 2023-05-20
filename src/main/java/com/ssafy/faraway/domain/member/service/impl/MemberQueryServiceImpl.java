package com.ssafy.faraway.domain.member.service.impl;

import com.ssafy.faraway.common.util.Encrypt;
import com.ssafy.faraway.domain.member.controller.dto.req.CheckLoginPwdRequest;
import com.ssafy.faraway.domain.member.controller.dto.req.FindLoginIdRequest;
import com.ssafy.faraway.domain.member.controller.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.controller.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.controller.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.repository.MemberQueryRepository;
import com.ssafy.faraway.domain.member.service.MemberQueryService;
import com.ssafy.faraway.domain.member.repository.dto.LoginEncMember;
import com.ssafy.faraway.domain.member.service.dto.LoginMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                .lastName(member.getName().getLastName())
                .firstName(member.getName().getFirstName())
                .birth(member.getBirth())
                .zipcode(member.getAddress().getZipcode())
                .mainAddress(member.getAddress().getMainAddress())
                .email(member.getEmail())
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
    public LoginMemberResponse login(LoginMemberDto dto) {
        Long id = memberQueryRepository.SearchIdByLoginId(dto.getLoginId());
        if(id == null){
            return null;
        }
        String salt = memberQueryRepository.SearchSaltById(id);
        String encLoginPwd = Encrypt.encrypt(dto.getLoginPwd(), salt);

        LoginEncMember loginEncMemberDto = LoginEncMember.builder()
                .loginId(dto.getLoginId())
                .loginPwd(encLoginPwd)
                .build();
        return memberQueryRepository.login(loginEncMemberDto);
    }

    @Override
    public String searchLoginId(FindLoginIdRequest request) {
        return memberQueryRepository.SearchLoginIdByEmailAndBirth(request);
    }

    @Override
    public boolean checkLoginPwd(CheckLoginPwdRequest request) {
        String loginPwd = memberQueryRepository.SearchLoginPwdById(request.getId());
        String salt = memberQueryRepository.SearchSaltById(request.getId());
        String inputPwd = Encrypt.encrypt(request.getLoginPwd(), salt);
        if(!loginPwd.equals(inputPwd)){
            return false;
        }
        return true;
    }

    @Override
    public String searchRefreshToken(Long memberId) {
        return memberQueryRepository.searchRefreshToken(memberId);
    }

    @Override
    public LoginMemberResponse searchLoginMemberById(Long memberId) {
        return memberQueryRepository.login(memberId);
    }


}

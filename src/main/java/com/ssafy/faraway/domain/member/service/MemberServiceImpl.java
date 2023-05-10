package com.ssafy.faraway.domain.member.service;

import com.ssafy.faraway.domain.member.dto.SaveMemberRequest;
import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import com.ssafy.faraway.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Long saveMember(SaveMemberRequest dto) {

        Member member = createMember(dto);
        System.out.println(member);
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }



    private Member createMember(SaveMemberRequest dto) {

        Name memberName = Name.builder()
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .build();

        Address memberAddress = Address.builder()
                .zipcode(dto.getZipcode())
                .mainAddress(dto.getMainAddress())
                .subAddress(dto.getSubAddress())
                .build();

        return Member.builder()
                .loginId(dto.getLoginId())
                .loginPwd(dto.getLoginPwd())
                .name(memberName)
                .birth(dto.getBirth())
                .email(dto.getEmail())
                .address(memberAddress)
                .salt(dto.getSalt())
                .role(Role.GUEST)
                .build();
    }
}

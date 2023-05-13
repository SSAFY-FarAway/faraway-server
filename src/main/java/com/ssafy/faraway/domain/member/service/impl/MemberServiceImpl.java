package com.ssafy.faraway.domain.member.service.impl;

import com.ssafy.faraway.common.util.Encrypt;
import com.ssafy.faraway.domain.member.dto.req.*;
import com.ssafy.faraway.domain.member.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import com.ssafy.faraway.domain.member.repository.MemberQueryRepository;
import com.ssafy.faraway.domain.member.repository.MemberRepository;
import com.ssafy.faraway.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberQueryRepository memberQueryRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long saveMember(SaveMemberRequest request) {
        SaveEncMember dto = createSaveMemberDto(request);
        Member member = createMember(dto);
//        System.out.println(member);
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    @Override
    public boolean checkLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        // 아이디가 있으면 true , 없으면 false
        return findMember.isPresent();
    }

    @Override
    public Long updateLoginPwd(UpdateLoginPwdRequest request) {
        Member findMember = memberRepository.findById(request.getId())
                .orElseThrow(NoSuchElementException::new);
        // 기존 암호화된 비밀번호
        String oldPwd = findMember.getLoginPwd();
        // salt
        String salt = findMember.getSalt();
        //유저가 입력한 기존 암호화된 비밀번호
        String inputPwd = Encrypt.encrypt(request.getCurrentLoginPwd(),salt);
        // 비교
        if(!oldPwd.equals(inputPwd)){
            return -1L;
        }
        String newPwd = Encrypt.encrypt(request.getNewLoginPwd(), salt);
        findMember.changeLoginPwd(inputPwd, newPwd);
        return findMember.getId();
    }

    @Override
    public Long updateMember(UpdateMemberRequest request) {
        Member findMember = memberRepository.findById(request.getId())
                .orElseThrow(NoSuchElementException::new);
        findMember.changeMember(request);
        return findMember.getId();
    }

    // 암호화한 비밀번호를 가진 DTO create
    private SaveEncMember createSaveMemberDto(SaveMemberRequest request){
        // encrypt password
        String salt = Encrypt.createSalt();
        String encodedLoginPwd = Encrypt.encrypt(request.getLoginPwd(), salt);

        // make Name, Address class
        Name memberName = Name.builder()
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .build();

        Address memberAddress = Address.builder()
                .zipcode(request.getZipcode())
                .mainAddress(request.getMainAddress())
                .subAddress(request.getSubAddress())
                .build();

        return SaveEncMember.builder()
                .loginId(request.getLoginId())
                .loginPwd(encodedLoginPwd)
                .name(memberName)
                .birth(request.getBirth())
                .email(request.getEmail())
                .address(memberAddress)
                .salt(salt)
                .build();
    }

    private Member createMember(SaveEncMember dto) {
        return Member.builder()
                .loginId(dto.getLoginId())
                .loginPwd(dto.getLoginPwd())
                .name(dto.getName())
                .birth(dto.getBirth())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .salt(dto.getSalt())
                .role(Role.GUEST)
                .build();
    }
}

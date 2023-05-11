package com.ssafy.faraway.domain.member.service.impl;

import com.ssafy.faraway.domain.member.dto.req.SaveEncMember;
import com.ssafy.faraway.domain.member.dto.req.SaveMemberRequest;
import com.ssafy.faraway.domain.member.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import com.ssafy.faraway.domain.member.repository.MemberRepository;
import com.ssafy.faraway.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long saveMember(SaveMemberRequest request) {
        SaveEncMember dto = createSaveMemberDto(request);
        Member member = createMember(dto);
//        System.out.println(member);
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }


    public String getSalt() {
        String salt="";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            salt = new String(Base64.getEncoder().encode(bytes));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }

    public String encrypt(String loginPwd, String hash) {
        String salt = hash+loginPwd;
        String hex = null;

        try {
            MessageDigest msg = MessageDigest.getInstance("SHA-512");
            msg.update(salt.getBytes());
            hex = String.format("%128x", new BigInteger(1, msg.digest()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hex;
    }

    // 암호화한 비밀번호를 가진 DTO create
    private SaveEncMember createSaveMemberDto(SaveMemberRequest request){
        // encrypt password
        String salt = getSalt();
        String encodedLoginPwd = encrypt(request.getLoginPwd(), salt);

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

package com.ssafy.faraway.domain.member.service.impl;

import com.ssafy.faraway.common.util.Encrypt;
import com.ssafy.faraway.domain.member.controller.dto.req.*;
import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import com.ssafy.faraway.domain.member.repository.MemberQueryRepository;
import com.ssafy.faraway.domain.member.repository.MemberRepository;
import com.ssafy.faraway.domain.member.service.MemberService;
import com.ssafy.faraway.domain.member.repository.dto.SaveEncMember;
import com.ssafy.faraway.domain.member.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public Long saveMember(SaveMemberDto dto) {
        SaveEncMember EncDto = createSaveMemberDto(dto);
        Member member = createMember(EncDto);
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
    public Long updateLoginPwd(UpdateLoginPwdDto dto) {
        Member findMember = memberRepository.findById(dto.getId())
                .orElseThrow(NoSuchElementException::new);
        // 기존 암호화된 비밀번호
        String oldPwd = findMember.getLoginPwd();
        // salt
        String salt = findMember.getSalt();
        //유저가 입력한 기존 암호화된 비밀번호
        String inputPwd = Encrypt.encrypt(dto.getCurrentLoginPwd(),salt);
        // 비교
        if(!oldPwd.equals(inputPwd)){
            return -1L;
        }
        String newPwd = Encrypt.encrypt(dto.getNewLoginPwd(), salt);
        findMember.changeLoginPwd(inputPwd, newPwd);
        return findMember.getId();
    }

    @Override
    public Long updateMember(UpdateMemberDto dto) {
        Member findMember = memberRepository.findById(dto.getId())
                .orElseThrow(NoSuchElementException::new);
        findMember.changeMember(dto);
        return findMember.getId();
    }

    @Override
    public Long resetLoginPwd(ResetLoginPwdDto dto) {
        Member findMember = memberRepository.findByLoginId(dto.getLoginId())
                .orElseThrow(NoSuchElementException::new);
        if(!dto.getBirth().equals(findMember.getBirth())){
            return -1L;
        }else if(!dto.getLoginId().equals(findMember.getLoginId())){
            return -1L;
        }else if(!dto.getEmail().equals(findMember.getEmail())){
            return -1L;
        }
        findMember.resetLoginPwd();
        return findMember.getId();
    }

    @Override
    public Long deleteMember(DeleteMemberDto dto) {
        Member findMember = memberRepository.findById(dto.getId())
                .orElseThrow(NoSuchElementException::new);
        String salt = findMember.getSalt();
        String inputPwd = Encrypt.encrypt(dto.getLoginPwd(), salt);
        if(!inputPwd.equals(findMember.getLoginPwd())){
            return -1L;
        }
        memberRepository.deleteById(dto.getId());
        return dto.getId();
    }

    @Override
    public Long saveRefreshToken(Long id, String refreshToken) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        findMember.saveRefreshToken(refreshToken);
        return findMember.getId();
    }

    @Override
    public Long deleteRefreshToken(Long id) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        findMember.deleteRefreshToken();
        return findMember.getId();
    }

    // 암호화한 비밀번호를 가진 DTO create
    private SaveEncMember createSaveMemberDto(SaveMemberDto dto){
        // encrypt password
        String salt = Encrypt.createSalt();
        String encodedLoginPwd = Encrypt.encrypt(dto.getLoginPwd(), salt);

        // make Name, Address class
        Name memberName = Name.builder()
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .build();

        Address memberAddress = Address.builder()
                .zipcode(dto.getZipcode())
                .mainAddress(dto.getMainAddress())
                .subAddress(dto.getSubAddress())
                .build();

        return SaveEncMember.builder()
                .loginId(dto.getLoginId())
                .loginPwd(encodedLoginPwd)
                .name(memberName)
                .birth(dto.getBirth())
                .email(dto.getEmail())
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

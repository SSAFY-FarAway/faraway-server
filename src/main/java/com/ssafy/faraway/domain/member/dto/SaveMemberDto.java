package com.ssafy.faraway.domain.member.dto;

import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
public class SaveMemberDto {
    private String loginId;
    private String loginPwd; // 암호화된 pwd
    private Name name;
    private String birth;
    private String email;
    private Address address;
    private String salt;

    @Builder
    public SaveMemberDto(String loginId, String loginPwd, Name name, String birth, String email, Address address, String salt) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.address = address;
        this.salt = salt;
    }


}




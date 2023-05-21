package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveMemberDto {

    private String loginId;
    private String loginPwd; // 암호화 x
    private String lastName;
    private String firstName;
    private String birth;
    private String email;
    private String zipcode;
    private String mainAddress;
    private String subAddress;


    @Builder
    public SaveMemberDto(String loginId, String loginPwd, String lastName, String firstName, String birth, String email, String zipcode, String mainAddress, String subAddress) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birth = birth;
        this.email = email;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
    }
}

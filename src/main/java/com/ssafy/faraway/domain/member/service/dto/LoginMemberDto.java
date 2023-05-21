package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class LoginMemberDto {
    private String loginId;
    private String loginPwd;

    @Builder
    public LoginMemberDto(String loginId, String loginPwd) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
    }
}

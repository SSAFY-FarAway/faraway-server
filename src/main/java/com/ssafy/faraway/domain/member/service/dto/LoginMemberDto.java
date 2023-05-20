package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginMemberDto {
    @NotEmpty(message = "loginId ust not be empty")
    @Size(min = 6, max = 20)
    private String loginId;
    @NotEmpty(message = "loginPwd must not be empty")
    @Size(min=8)
    private String loginPwd;

    @Builder
    public LoginMemberDto(String loginId, String loginPwd) {
        this.loginId = loginId;
        this.loginPwd = loginPwd;
    }
}

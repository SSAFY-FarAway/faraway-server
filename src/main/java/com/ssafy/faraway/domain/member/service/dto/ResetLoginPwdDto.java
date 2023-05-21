package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;



@Data
public class ResetLoginPwdDto {
    private String loginId;
    private String birth;
    private String email;

    @Builder
    public ResetLoginPwdDto(String loginId, String birth, String email) {
        this.loginId = loginId;
        this.birth = birth;
        this.email = email;
    }
}

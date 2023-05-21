package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CheckLoginPwdDto {
    private Long id;
    private String loginPwd;

    @Builder
    public CheckLoginPwdDto(Long id, String loginPwd) {
        this.id = id;
        this.loginPwd = loginPwd;
    }
}

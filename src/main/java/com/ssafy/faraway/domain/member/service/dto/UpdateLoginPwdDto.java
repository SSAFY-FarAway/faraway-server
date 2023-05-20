package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateLoginPwdDto {
    private Long id;
    private String currentLoginPwd; //기존 비밀번호
    private String newLoginPwd; // 바뀔 비밀번호

    @Builder
    public UpdateLoginPwdDto(Long id, String currentLoginPwd, String newLoginPwd) {
        this.id = id;
        this.currentLoginPwd = currentLoginPwd;
        this.newLoginPwd = newLoginPwd;
    }
}

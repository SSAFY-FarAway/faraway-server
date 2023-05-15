package com.ssafy.faraway.domain.member.dto.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Data
public class UpdateLoginPwdRequest {
    private Long id;
    @NotEmpty(message = "loginPwd must not be empty")
    @Size(min=8)
    private String currentLoginPwd; //기존 비밀번호
    @NotEmpty(message = "loginPwd must not be empty")
    @Size(min=8)
    private String newLoginPwd; // 바뀔 비밀번호
}

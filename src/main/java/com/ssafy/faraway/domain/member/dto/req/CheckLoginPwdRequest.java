package com.ssafy.faraway.domain.member.dto.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CheckLoginPwdRequest {
    private Long id;
    @NotEmpty(message = "loginPwd must not be empty")
    @Size(min=8)
    private String loginPwd;
}

package com.ssafy.faraway.domain.member.controller.dto.req;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Data
public class SaveMemberRequest {
    @NotEmpty(message = "loginId ust not be empty")
    @Size(min = 6, max = 20)
    private String loginId;
    @NotEmpty(message = "loginPwd must not be empty")
    @Size(min=8)
    private String loginPwd; // 암호화 x
    @NotEmpty(message = "lastName must not be empty")
    @Size(max=20)
    private String lastName;
    @NotEmpty(message = "firstName must not be empty")
    @Size(max=30)
    private String firstName;
    @NotEmpty(message = "birth's size must not be 6")
    @Size(min=6, max=6)
    private String birth;
    @NotEmpty(message = "email must not be empty")
    @Email
    private String email;
    @NotEmpty(message = "zipcode's size must not be 6")
    @Size(min=5, max=5)
    private String zipcode;
    @NotEmpty(message = "mainAddress must not be empty")
    private String mainAddress;
    @NotEmpty(message = "subAddress must not be empty")
    private String subAddress;
}

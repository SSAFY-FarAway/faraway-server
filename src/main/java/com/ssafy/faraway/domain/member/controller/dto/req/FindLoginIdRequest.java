package com.ssafy.faraway.domain.member.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class FindLoginIdRequest {
    @NotEmpty(message = "birth's size must not be 6")
    @Size(min=6, max=6)
    private String birth;
    @NotEmpty(message = "email must not be empty")
    @Email
    private String email;
}

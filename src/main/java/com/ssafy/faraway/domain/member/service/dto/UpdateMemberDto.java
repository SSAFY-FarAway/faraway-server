package com.ssafy.faraway.domain.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateMemberDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String birth;
    private String email;
    private String zipcode;
    private String mainAddress;
    private String subAddress;

    @Builder
    public UpdateMemberDto(Long id, String lastName, String firstName, String birth, String email, String zipcode, String mainAddress, String subAddress) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birth = birth;
        this.email = email;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
    }
}

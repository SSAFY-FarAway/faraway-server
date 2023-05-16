package com.ssafy.faraway.domain.member.dto.res;

import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
public class MemberResponse {
    private Long id;
    private String loginId;
    private String lastName;
    private String firstName;
    private String birth;
    private String zipcode;
    private String mainAddress;
    private String subAddress;
    private String email;
    private int mileage;
    private Role role;
    private int certified;

    @Builder
    public MemberResponse(Long id, String loginId, String lastName, String firstName, String birth, String zipcode, String mainAddress, String subAddress, String email, int mileage, Role role, int certified) {
        this.id = id;
        this.loginId = loginId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birth = birth;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.email = email;
        this.subAddress = subAddress;
        this.mileage = mileage;
        this.role = role;
        this.certified = certified;
    }

}

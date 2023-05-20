package com.ssafy.faraway.domain.member.controller.dto.res;

import com.ssafy.faraway.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginMemberResponse {
    private long id;
    private String loginId;
    private String lastName;
    private String firstName;
    private int mileage;
    private Role role;

    @Builder
    public LoginMemberResponse(long id, String loginId, String lastName, String firstName, int mileage, Role role) {
        this.id = id;
        this.loginId = loginId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mileage = mileage;
        this.role = role;
    }
}

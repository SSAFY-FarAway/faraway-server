package com.ssafy.faraway.domain.member.dto.res;

import com.ssafy.faraway.domain.member.entity.Address;
import com.ssafy.faraway.domain.member.entity.Name;
import com.ssafy.faraway.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ListMemberResponse {
    private Long id;
    private String loginId;
    private String lastName;
    private String firstName;
    private String mainAddress;
    private String birth;
    private String email;
    private int mileage;
    private Role role;
    private int certified;

    @Builder
    public ListMemberResponse(Long id, String loginId, String lastName, String firstName, String mainAddress, String birth, String email, int mileage, Role role, int certified) {
        this.id = id;
        this.loginId = loginId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mainAddress = mainAddress;
        this.birth = birth;
        this.email = email;
        this.mileage = mileage;
        this.role = role;
        this.certified = certified;
    }
}

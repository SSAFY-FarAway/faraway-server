package com.ssafy.faraway.domain.member.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.common.util.Encrypt;
import com.ssafy.faraway.domain.member.controller.dto.req.UpdateMemberRequest;
import com.ssafy.faraway.domain.member.service.dto.UpdateMemberDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String loginId;
    @Column(nullable = false)
    private String loginPwd;
    @Embedded
    private Name name;
    @Column(nullable = false, length = 6)
    private String birth;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Embedded
    private Address address;
    @Column(nullable = false, length = 45)
    private String salt;
    @Column(nullable = false)
    private int mileage;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private int certified;
    @Column(nullable = true, length = 1000)
    private String token;

    @Builder
    public Member(Long id, String loginId, String loginPwd, Name name, String birth, String email, Address address, String salt, int mileage, Role role, int certified, String token) {
        this.id = id;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.address = address;
        this.salt = salt;
        this.mileage = mileage;
        this.role = role;
        this.certified = certified;
        this.token = token;
    }

    // 비즈니스 로직
    public void changeLoginPwd(String currentLoginPw, String newLoginPw){
        if (!this.loginPwd.equals(currentLoginPw)) {
            throw new CustomException(ErrorCode.BAD_REQUEST_SAME_PWD_TWICE);
        }
        this.loginPwd = newLoginPw;
    }

    public void changeMember(UpdateMemberDto dto){
        Name updateName = Name.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        Address updateAddress = Address.builder()
                .mainAddress(dto.getMainAddress())
                .subAddress(dto.getSubAddress())
                .zipcode(dto.getZipcode())
                .build();
        this.name = updateName;
        this.birth = dto.getBirth();
        this.email = dto.getEmail();
        this.address = updateAddress;
    }

    public void resetLoginPwd(){
        this.loginPwd = Encrypt.encrypt("00000000", this.salt);
    }

    public void saveRefreshToken(String refreshToken){
        this.token = refreshToken;
    }

    public void deleteRefreshToken(){
        this.token = null;
    }

}
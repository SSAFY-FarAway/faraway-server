package com.ssafy.faraway.domain.member.entity;

import com.ssafy.faraway.common.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
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
    @ColumnDefault("0")
    private Long mileage;
    @Column(nullable = false)
    @ColumnDefault("1")
    private Role role;
    @Column(nullable = false)
    @ColumnDefault("0")
    private int certified;

    @Builder
    public Member(Long id, String loginId, String loginPwd, Name name, String birth, String email, Address address, String salt, Long mileage, Role role, int certified) {
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
    }
}
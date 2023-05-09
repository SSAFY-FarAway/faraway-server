package com.ssafy.faraway.domain.member.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Name {

    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false, length = 30)
    private String firstName;
}

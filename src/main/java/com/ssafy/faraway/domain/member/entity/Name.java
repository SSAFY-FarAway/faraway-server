package com.ssafy.faraway.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Name {

    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false, length = 30)
    private String firstName;

    @Builder
    public Name(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}

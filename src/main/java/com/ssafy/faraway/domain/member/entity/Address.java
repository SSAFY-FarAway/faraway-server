package com.ssafy.faraway.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    @Column(nullable = false, length = 5)
    private String zipcode;
    @Column(nullable = false)
    private String mainAddress;
    @Column(nullable = false)
    private String subAddress;

    @Builder
    public Address(String zipcode, String mainAddress, String subAddress) {
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
    }
}

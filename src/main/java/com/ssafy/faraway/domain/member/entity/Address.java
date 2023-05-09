package com.ssafy.faraway.domain.member.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    @Column(nullable = false, length = 5)
    private String zipcode;
    @Column(nullable = false)
    private String mainAddress;
    @Column(nullable = false)
    private String subAddress;
}

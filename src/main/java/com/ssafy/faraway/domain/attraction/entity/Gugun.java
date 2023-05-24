package com.ssafy.faraway.domain.attraction.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gugun {
    @Id
    private Integer gugunCode;
    @Column(length = 30)
    private String gugunName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    @Builder
    public Gugun(Integer gugunCode, String gugunName, Sido sido) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
        this.sido = sido;
    }
}

package com.ssafy.faraway.domain.attraction.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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

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
    @Column(name = "gugun_code")
    private int gugunCode;
    @Column(name = "gugun_name")
    private String gugunName;
    @ManyToOne
    @JoinColumn(name = "sido_code", insertable = false, updatable = false)
    private Sido sido;

    @Builder
    public Gugun(int gugunCode, String gugunName, Sido sido) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
        this.sido = sido;
    }
}

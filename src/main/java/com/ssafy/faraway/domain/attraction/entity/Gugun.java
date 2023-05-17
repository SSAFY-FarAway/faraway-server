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
    private int gugunCode;
    private String gugunName;
    @ManyToOne
    @JoinColumn(name = "sido_code")
    private Sido sido;

    @Builder
    public Gugun(int gugunCode, String gugunName, Sido sido) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
        this.sido = sido;
    }
}

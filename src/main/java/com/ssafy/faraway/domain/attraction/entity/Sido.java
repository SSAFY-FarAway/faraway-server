package com.ssafy.faraway.domain.attraction.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Sido {
    @Id
    private Integer sidoCode;
    private String sidoName;

    @Builder
    public Sido(Integer sidoCode, String sidoName) {
        this.sidoCode = sidoCode;
        this.sidoName = sidoName;
    }
}

package com.ssafy.faraway.domain.attraction.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SidoResponse {
    private Integer sidoCode;
    private String sidoName;

    @Builder
    public SidoResponse(Integer sidoCode, String sidoName) {
        this.sidoCode = sidoCode;
        this.sidoName = sidoName;
    }
}

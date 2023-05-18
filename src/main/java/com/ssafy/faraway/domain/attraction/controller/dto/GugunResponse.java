package com.ssafy.faraway.domain.attraction.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class GugunResponse {
    int gugunCode;
    String gugunName;

    @Builder
    public GugunResponse(int gugunCode, String gugunName) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
    }
}

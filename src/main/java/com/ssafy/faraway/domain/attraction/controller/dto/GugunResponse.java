package com.ssafy.faraway.domain.attraction.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GugunResponse {
    int gugunCode;
    String gugunName;

    @Builder
    public GugunResponse(int gugunCode, String gugunName) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
    }
}

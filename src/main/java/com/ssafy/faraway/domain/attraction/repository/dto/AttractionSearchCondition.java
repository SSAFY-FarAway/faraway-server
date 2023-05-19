package com.ssafy.faraway.domain.attraction.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AttractionSearchCondition {
    private Integer sidoCode;
    private Integer gugunCode;
    private Integer contentTypeId;

    @Builder
    public AttractionSearchCondition(Integer sidoCode, Integer gugunCode, Integer contentTypeId) {
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.contentTypeId = contentTypeId;
    }
}

package com.ssafy.faraway.domain.attraction.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AttractionSearchCondition {
    private Integer sidoCode;
    private Integer gugunCode;
    private Integer contentTypeId;
    private String title;
    private String address;

    @Builder
    public AttractionSearchCondition(Integer sidoCode, Integer gugunCode, Integer contentTypeId, String title, String address) {
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.address = address;
    }
}

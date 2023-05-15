package com.ssafy.faraway.domain.attraction.dto.req;

import lombok.Data;

@Data
public class AttractionSearchCondition {
    private Integer sidoCode;
    private Integer gugunCode;
    private Integer contentTypeId;
}

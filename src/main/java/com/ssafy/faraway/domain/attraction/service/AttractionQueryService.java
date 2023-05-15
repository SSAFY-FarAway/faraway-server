package com.ssafy.faraway.domain.attraction.service;

import com.ssafy.faraway.domain.attraction.dto.req.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import com.ssafy.faraway.domain.attraction.dto.res.GugunResponse;

import java.util.List;

public interface AttractionQueryService {
    List<AttractionResponse> searchAll();
    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);
    List<AttractionResponse> searchAllByIds(List<Integer> ids);
    List<GugunResponse> searchGugunBySidoCode(Integer sidoCode);
}

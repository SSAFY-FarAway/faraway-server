package com.ssafy.faraway.domain.attraction.repository;

import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;

import java.util.List;

public interface AttractionQueryRepository {
    List<AttractionResponse> searchAll();
    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);
    List<AttractionResponse> searchAllByIds(List<Integer> ids);

    List<GugunResponse> searchGugunBySidoCode(Integer sidoCode);
}

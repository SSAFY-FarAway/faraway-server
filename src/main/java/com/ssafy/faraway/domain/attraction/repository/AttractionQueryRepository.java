package com.ssafy.faraway.domain.attraction.repository;

import com.ssafy.faraway.domain.attraction.dto.req.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import com.ssafy.faraway.domain.attraction.dto.res.GugunResponse;
import com.ssafy.faraway.domain.attraction.dto.res.SidoResponse;
import com.ssafy.faraway.domain.attraction.entity.Gugun;

import java.util.List;

public interface AttractionQueryRepository {
    List<AttractionResponse> searchAll();
    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);
    List<AttractionResponse> searchAllByIds(List<Integer> ids);

    List<GugunResponse> searchGugunBySidoCode(Integer sidoCode);
}

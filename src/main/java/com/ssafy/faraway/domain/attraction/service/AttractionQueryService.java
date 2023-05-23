package com.ssafy.faraway.domain.attraction.service;

import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttractionQueryService {
    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition, Long memberId, Pageable pageable);

    List<GugunResponse> searchGugunBySidoCode(Integer sidoCode);

    int getPageTotalCnt(AttractionSearchCondition condition);
}

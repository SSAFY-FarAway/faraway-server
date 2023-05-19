package com.ssafy.faraway.domain.attraction.repository;

import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttractionQueryRepository {
    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition, Pageable pageable);

    List<AttractionResponse> SearchByIds(List<Integer> ids);

    List<GugunResponse> searchGugunBySidoCode(Integer sidoCode);

    int getPageTotalCnt(AttractionSearchCondition condition);
}

package com.ssafy.faraway.domain.attraction.repository;

import com.ssafy.faraway.domain.attraction.entity.AttractionInfo;
import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttractionQueryRepository {
    List<AttractionInfo> searchByCondition(AttractionSearchCondition condition, Long memberId, Pageable pageable);

    List<AttractionResponse> SearchByIds(List<Integer> ids);

    List<GugunResponse> searchGugunBySidoCode(Integer sidoCode);

    int getPageTotalCnt(AttractionSearchCondition condition);
}

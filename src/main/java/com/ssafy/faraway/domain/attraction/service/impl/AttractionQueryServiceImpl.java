package com.ssafy.faraway.domain.attraction.service.impl;

import com.ssafy.faraway.common.util.SizeConstants;
import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import com.ssafy.faraway.domain.attraction.repository.AttractionQueryRepository;
import com.ssafy.faraway.domain.attraction.service.AttractionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.faraway.common.util.SizeConstants.ATTRACTION_SIZE;

@Service
@RequiredArgsConstructor
public class AttractionQueryServiceImpl implements AttractionQueryService {
    private final AttractionQueryRepository attractionQueryRepository;

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition, Pageable pageable) {
        return attractionQueryRepository.searchByCondition(condition, pageable);
    }

    @Override
    public List<GugunResponse> searchGugunBySidoCode(Integer sidoCode) {
        return attractionQueryRepository.searchGugunBySidoCode(sidoCode);
    }

    @Override
    public int getPageTotalCnt(AttractionSearchCondition condition) {
        return ((attractionQueryRepository.getPageTotalCnt(condition) - 1) / ATTRACTION_SIZE) + 1;
    }
}

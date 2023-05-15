package com.ssafy.faraway.domain.attraction.service.impl;

import com.ssafy.faraway.domain.attraction.dto.req.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import com.ssafy.faraway.domain.attraction.dto.res.GugunResponse;
import com.ssafy.faraway.domain.attraction.repository.AttractionQueryRepository;
import com.ssafy.faraway.domain.attraction.service.AttractionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionQueryServiceImpl implements AttractionQueryService {
    private final AttractionQueryRepository attractionQueryRepository;

    @Override
    public List<AttractionResponse> searchAll() {
        return attractionQueryRepository.searchAll();
    }

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition) {
        return attractionQueryRepository.searchByCondition(condition);
    }

    @Override
    public List<AttractionResponse> searchAllByIds(List<Integer> ids) {
        return attractionQueryRepository.searchAllByIds(ids);
    }

    @Override
    public List<GugunResponse> searchGugunBySidoCode(Integer sidoCode) {
        return attractionQueryRepository.searchGugunBySidoCode(sidoCode);
    }
}

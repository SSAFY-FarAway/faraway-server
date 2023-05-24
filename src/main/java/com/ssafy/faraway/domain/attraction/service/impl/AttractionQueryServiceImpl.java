package com.ssafy.faraway.domain.attraction.service.impl;

import com.ssafy.faraway.domain.attraction.entity.AttractionInfo;
import com.ssafy.faraway.domain.attraction.repository.AttractionLikeQueryRepository;
import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import com.ssafy.faraway.domain.attraction.repository.AttractionQueryRepository;
import com.ssafy.faraway.domain.attraction.service.AttractionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.common.util.SizeConstants.ATTRACTION_SIZE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttractionQueryServiceImpl implements AttractionQueryService {
    private final AttractionQueryRepository attractionQueryRepository;
    private final AttractionLikeQueryRepository attractionLikeQueryRepository;

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition, Long memberId, Pageable pageable) {
        List<AttractionInfo> attractionInfos = attractionQueryRepository.searchByCondition(condition, memberId, pageable);
        List<AttractionResponse> responses = new ArrayList<>();
        for (AttractionInfo info : attractionInfos) {
            responses.add(AttractionResponse.builder()
                    .contentId(info.getContentId())
                    .title(info.getTitle())
                    .addr1(info.getAddr1())
                    .addr2(info.getAddr2())
                    .zipcode(info.getZipcode())
                    .tel(info.getTel())
                    .firstImage(info.getFirstImage())
                    .latitude(info.getLatitude())
                    .longitude(info.getLongitude())
                    .overview(info.getAttractionDesc().getOverview())
                    .likeCnt(info.getLikes().size())
                    .likeId(attractionLikeQueryRepository.searchLikeId(memberId, info.getContentId()))
                    .build());
        }
        return responses;
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

package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.DetailHotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.HotPlaceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface HotPlaceQueryService {
    DetailHotPlaceResponse searchById(Long hotPlaceId, Long memberId);

    List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable);
    int getPageTotalCnt(HotPlaceSearchCondition condition);
}

package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.ListHotPlaceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface HotPlaceQueryService {
    HotPlaceResponse searchById(Long hotPlaceId);

    List<ListHotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable);
    int getPageTotalCnt(HotPlaceSearchCondition condition);
}

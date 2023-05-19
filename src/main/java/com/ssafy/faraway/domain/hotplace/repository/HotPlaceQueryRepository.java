package com.ssafy.faraway.domain.hotplace.repository;

import com.ssafy.faraway.domain.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotPlaceQueryRepository {
    HotPlace searchById(Long hotPlaceId);

    List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable);

    int getPageTotalCnt(HotPlaceSearchCondition condition);
}

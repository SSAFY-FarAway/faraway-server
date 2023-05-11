package com.ssafy.faraway.domain.hotplace.repository;

import com.ssafy.faraway.domain.hotplace.dto.req.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.dto.res.ListHotPlaceResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotPlaceQueryRepository {
    List<ListHotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable);
}

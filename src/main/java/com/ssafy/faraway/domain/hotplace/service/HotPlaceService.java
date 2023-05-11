package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceService {
    Long save(Long memberId, SaveHotPlaceRequest request);
}

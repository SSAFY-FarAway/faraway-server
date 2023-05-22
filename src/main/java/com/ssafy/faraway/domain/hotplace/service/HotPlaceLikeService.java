package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceLikeDto;

public interface HotPlaceLikeService {
    Long save(SaveHotPlaceLikeDto dto);
    Long delete(Long likeId);
}

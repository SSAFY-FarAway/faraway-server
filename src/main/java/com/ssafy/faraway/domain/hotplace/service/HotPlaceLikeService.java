package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceLikeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceLikeService {
    Long save(SaveHotPlaceLikeDto dto);
    Long delete(Long likeId);
}

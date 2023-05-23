package com.ssafy.faraway.domain.attraction.service;

import com.ssafy.faraway.domain.attraction.service.dto.SaveAttractionLikeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AttractionLikeService {
    Long save(SaveAttractionLikeDto dto);
    Long delete(Long likeId);
}

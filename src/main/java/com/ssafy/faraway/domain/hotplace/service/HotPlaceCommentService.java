package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceCommentRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceCommentService {
    Long save(Long hotPlaceId, Long memberId, SaveHotPlaceCommentRequest request);
}

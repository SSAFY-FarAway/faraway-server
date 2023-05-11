package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceCommentRequest;
import com.ssafy.faraway.domain.hotplace.dto.req.UpdateHotPlaceCommentRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceCommentService {
    Long save(Long hotPlaceId, Long memberId, SaveHotPlaceCommentRequest request);

    Long update(Long commentId, UpdateHotPlaceCommentRequest request);

    Long delete(Long commentId);
}

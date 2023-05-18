package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceCommentDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceCommentService {
    Long save(Long hotPlaceId, Long memberId, SaveHotPlaceCommentDto dto);

    Long update(Long commentId, UpdateHotPlaceCommentDto dto);

    Long delete(Long commentId);
}

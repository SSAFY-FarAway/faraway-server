package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceCommentDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceCommentService {
    Long save(SaveHotPlaceCommentDto dto);

    Long update(UpdateHotPlaceCommentDto dto);

    Long delete(Long commentId);
}

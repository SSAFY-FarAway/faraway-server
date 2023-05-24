package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
public interface HotPlaceService {
    Long save(SaveHotPlaceDto dto) throws IOException;

    Long update(UpdateHotPlaceDto dto);

    Long delete(Long hotPlaceId);
}

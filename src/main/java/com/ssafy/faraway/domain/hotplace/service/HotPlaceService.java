package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public interface HotPlaceService {
    Long save(Long memberId, SaveHotPlaceDto dto, List<UploadFile> uploadFiles) throws IOException;

    Long update(Long hotPlaceId, UpdateHotPlaceDto dto, List<UploadFile> uploadFiles);

    Long delete(Long hotPlaceId);
}

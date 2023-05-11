package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceRequest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
public interface HotPlaceService {
    Long save(Long memberId, SaveHotPlaceRequest request, List<UploadFile> uploadFiles) throws IOException;
}

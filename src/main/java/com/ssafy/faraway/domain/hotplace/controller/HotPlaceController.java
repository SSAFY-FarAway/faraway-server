package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.common.util.FileExtChecker;
import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotplace")
@Slf4j
@Api(tags = "hotPlace")
public class HotPlaceController {
    private final HotPlaceService hotPlaceService;
    private final FileStore fileStore;
    private final FileExtChecker fileExtChecker;

    @PostMapping
    public Long saveHotPlace(@Valid @RequestPart(value = "request") SaveHotPlaceRequest request,
                             @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {
        // TODO: 2023-05-11 로그인 기능 구현 시 수정해야함
        Long memberId = 1L;
        List<UploadFile> uploadFiles = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            fileExtChecker.checkExtension(files);
            uploadFiles = fileStore.storeFiles(files);
        }

        Long saveId = hotPlaceService.save(memberId, request, uploadFiles);
        log.debug("saveHotPlaceId: {}", saveId);
        return saveId;
    }


}

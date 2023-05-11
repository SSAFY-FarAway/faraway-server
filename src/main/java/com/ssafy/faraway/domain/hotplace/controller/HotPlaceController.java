package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.util.FileExtChecker;
import com.ssafy.faraway.domain.hotplace.dto.req.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceCommentRequest;
import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.dto.req.UpdateHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.dto.res.ListHotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceCommentService;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceQueryService;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
    private final HotPlaceQueryService hotPlaceQueryService;
    private final HotPlaceCommentService hotPlaceCommentService;
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

    @GetMapping
    public ResultPage<List<ListHotPlaceResponse>> searchHotPlace(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        HotPlaceSearchCondition condition = HotPlaceSearchCondition.builder()
                .title(title)
                .content(content)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber -1 , 10);
        List<ListHotPlaceResponse> responses = hotPlaceQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, 10);
    }

    @GetMapping("/{hotPlaceId}")
    public HotPlaceResponse searchHotPlace(@PathVariable Long hotPlaceId) {
        return hotPlaceQueryService.searchById(hotPlaceId);
    }

    @PutMapping("/{hotPlaceId}")
    public Long updateHotPlace(@PathVariable Long hotPlaceId, @Valid @RequestBody UpdateHotPlaceRequest request) {
        return hotPlaceService.update(hotPlaceId, request);
    }

    @DeleteMapping("/{hotPlaceId}")
    public Long updateHotPlace(@PathVariable Long hotPlaceId) {
        return hotPlaceService.delete(hotPlaceId);
    }

    @PostMapping("{hotPlaceId}/comment")
    public Long saveHotPlaceComment(@PathVariable Long hotPlaceId, @Valid @RequestBody SaveHotPlaceCommentRequest request){
        // TODO: 2023-05-11 로그인 구현 시 수정
        Long memberId = 1L;
        return hotPlaceCommentService.save(hotPlaceId, memberId, request);
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

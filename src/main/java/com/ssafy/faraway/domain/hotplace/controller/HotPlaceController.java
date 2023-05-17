package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.util.FileExtFilter;
import com.ssafy.faraway.domain.hotplace.dto.req.*;
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
@CrossOrigin(originPatterns = "*")
@Slf4j
@Api(tags = "hotPlace")
public class HotPlaceController {
    private final HotPlaceService hotPlaceService;
    private final HotPlaceQueryService hotPlaceQueryService;
    private final HotPlaceCommentService hotPlaceCommentService;
    private final FileStore fileStore;
    private final FileExtFilter fileExtFilter;

    @PostMapping
    public Long saveHotPlace(@Valid @RequestPart(value = "request") SaveHotPlaceRequest request,
                             @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {
        // TODO: 2023-05-11 로그인 기능 구현 시 수정해야함
        Long memberId = 1L;
        List<UploadFile> uploadFiles = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            fileExtFilter.imageFilter(files);
            uploadFiles = fileStore.storeFiles(files);
        }

        return hotPlaceService.save(memberId, request, uploadFiles);
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
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<ListHotPlaceResponse> responses = hotPlaceQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, 10);
    }

    @GetMapping("/{hotPlaceId}")
    public HotPlaceResponse searchHotPlace(@PathVariable Long hotPlaceId) {
        return hotPlaceQueryService.searchById(hotPlaceId);
    }

    @PutMapping("/{hotPlaceId}")
    public Long updateHotPlace(@PathVariable Long hotPlaceId,
                               @Valid @RequestPart(value = "request") UpdateHotPlaceRequest request,
                               @RequestPart(value = "files") List<MultipartFile> files) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            fileExtFilter.imageFilter(files);
            uploadFiles = fileStore.storeFiles(files);
        }
        return hotPlaceService.update(hotPlaceId, request, uploadFiles);
    }

    @DeleteMapping("/{hotPlaceId}")
    public Long updateHotPlace(@PathVariable Long hotPlaceId) {
        return hotPlaceService.delete(hotPlaceId);
    }

    @PostMapping("{hotPlaceId}/comment")
    public Long saveHotPlaceComment(@PathVariable Long hotPlaceId, @Valid @RequestBody SaveHotPlaceCommentRequest request) {
        // TODO: 2023-05-11 로그인 구현 시 수정
        Long memberId = 1L;
        return hotPlaceCommentService.save(hotPlaceId, memberId, request);
    }

    @PutMapping("/comment/{commentId}")
    public Long updateHotPlaceComment(@PathVariable Long commentId, @Valid @RequestBody UpdateHotPlaceCommentRequest request) {
        return hotPlaceCommentService.update(commentId, request);
    }

    @DeleteMapping("/comment/{commentId}")
    public Long deleteHotPlaceComment(@PathVariable Long commentId) {
        return hotPlaceCommentService.delete(commentId);
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

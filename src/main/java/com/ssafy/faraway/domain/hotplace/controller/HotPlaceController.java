package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.common.domain.ResultPage;
import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.common.util.FileExtFilter;
import com.ssafy.faraway.domain.hotplace.controller.dto.req.*;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.DetailHotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceCommentService;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceQueryService;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceService;
import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceCommentDto;
import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceCommentDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceDto;
import com.ssafy.faraway.domain.member.service.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.common.util.SizeConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hot-place")
@Slf4j
@Api(tags = "hotPlace")
public class HotPlaceController {
    private final HotPlaceService hotPlaceService;
    private final HotPlaceQueryService hotPlaceQueryService;
    private final HotPlaceCommentService hotPlaceCommentService;
    private final FileStore fileStore;
    private final FileExtFilter fileExtFilter;
    private final JwtService jwtService;

    @PostMapping
    public Long saveHotPlace(@Valid @RequestPart SaveHotPlaceRequest request,
                             @RequestPart(required = false) List<MultipartFile> files) throws IOException {
        Long loginId = jwtService.getMemberId();
        List<UploadFile> uploadFiles = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            fileExtFilter.imageFilter(files);
            uploadFiles = fileStore.storeFiles(files);
        }

        SaveHotPlaceDto dto = SaveHotPlaceDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .zipcode(request.getZipcode())
                .mainAddress(request.getMainAddress())
                .subAddress(request.getSubAddress())
                .rating(request.getRating())
                .build();

        return hotPlaceService.save(loginId, dto, uploadFiles);
    }

    @GetMapping
    public ResultPage<List<HotPlaceResponse>> searchHotPlace(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        HotPlaceSearchCondition condition = HotPlaceSearchCondition.builder()
                .title(title)
                .content(content)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<HotPlaceResponse> responses = hotPlaceQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, PAGE_SIZE, hotPlaceQueryService.getPageTotalCnt(condition));
    }

    @GetMapping("/{hotPlaceId}")
    public DetailHotPlaceResponse searchHotPlace(@PathVariable Long hotPlaceId) {
        Long loginId = jwtService.getMemberId();
        DetailHotPlaceResponse response = hotPlaceQueryService.searchById(hotPlaceId, loginId);
        if (response == null) {
            throw new CustomException(ErrorCode.HOT_PLACE_NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{hotPlaceId}")
    public Long updateHotPlace(@PathVariable Long hotPlaceId,
                               @Valid @RequestPart UpdateHotPlaceRequest request,
                               @RequestPart(required = false) List<MultipartFile> files) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            fileExtFilter.imageFilter(files);
            uploadFiles = fileStore.storeFiles(files);
        }

        UpdateHotPlaceDto dto = UpdateHotPlaceDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .zipcode(request.getZipcode())
                .mainAddress(request.getMainAddress())
                .subAddress(request.getSubAddress())
                .rating(request.getRating())
                .deleteImageIds(request.getDeleteImageIds())
                .build();

        return hotPlaceService.update(hotPlaceId, dto, uploadFiles);
    }

    @DeleteMapping("/{hotPlaceId}")
    public Long deleteHotPlace(@PathVariable Long hotPlaceId) {
        return hotPlaceService.delete(hotPlaceId);
    }

    @PostMapping("/{hotPlaceId}/comment")
    public Long saveHotPlaceComment(@PathVariable Long hotPlaceId,
                                    @Valid @RequestBody SaveHotPlaceCommentRequest request) {
        Long loginId = jwtService.getMemberId();
        SaveHotPlaceCommentDto dto = SaveHotPlaceCommentDto.builder()
                .content(request.getContent()).build();
        return hotPlaceCommentService.save(hotPlaceId, loginId, dto);
    }

    @PutMapping("/comment/{commentId}")
    public Long updateHotPlaceComment(@PathVariable Long commentId,
                                      @Valid @RequestBody UpdateHotPlaceCommentRequest request) {
        UpdateHotPlaceCommentDto dto = UpdateHotPlaceCommentDto.builder()
                .content(request.getContent()).build();
        return hotPlaceCommentService.update(commentId, dto);
    }

    @DeleteMapping("/comment/{commentId}")
    public Long deleteHotPlaceComment(@PathVariable Long commentId) {
        return hotPlaceCommentService.delete(commentId);
    }
}

package com.ssafy.faraway.domain.hotplace.controller;

import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotplace")
@Slf4j
@Api(tags = "hotPlace")
public class HotPlaceController {
    private final HotPlaceService hotPlaceService;
    @PostMapping
    public Long saveHotPlace(@Valid @RequestBody SaveHotPlaceRequest request) {
        // TODO: 2023-05-11 로그인 기능 구현 시 수정해야함
        Long memberId = 1L;
        Long saveId = hotPlaceService.save(memberId, request);
        log.debug("saveHotPlaceId: {}", saveId);
        return saveId;
    }
}

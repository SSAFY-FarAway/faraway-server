package com.ssafy.faraway.domain.attraction.controller;

import com.ssafy.faraway.common.domain.ResultPage;
import com.ssafy.faraway.common.util.SizeConstants;
import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import com.ssafy.faraway.domain.attraction.entity.Sido;
import com.ssafy.faraway.domain.attraction.service.AttractionQueryService;
import com.ssafy.faraway.domain.attraction.service.AttractionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.ssafy.faraway.common.util.SizeConstants.ATTRACTION_SIZE;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/attraction")
@Api(tags = "attraction")
public class AttractionController {
    private final AttractionService attractionService;
    private final AttractionQueryService attractionQueryService;

    @GetMapping("/sido")
    public List<Sido> findSidos() {
        return attractionService.findAllSido();
    }

    @GetMapping("/gugun/{sidoCode}")
    public List<GugunResponse> searchGuguns(@PathVariable int sidoCode) {
        return attractionQueryService.searchGugunBySidoCode(sidoCode);
    }

    @GetMapping
    public ResultPage<List<AttractionResponse>> searchAttractions(
            @RequestParam(required = false, defaultValue = "") Integer sidoCode,
            @RequestParam(required = false, defaultValue = "") Integer gugunCode,
            @RequestParam(required = false, defaultValue = "") Integer contentTypeId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String address) {
        AttractionSearchCondition condition = AttractionSearchCondition.builder()
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .contentTypeId(contentTypeId)
                .title(title)
                .address(address)
                .build();
        log.debug("condition: {}", condition);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, ATTRACTION_SIZE);
        List<AttractionResponse> responses = attractionQueryService.searchByCondition(condition, pageRequest);
        log.debug("responses size: {}", responses.size());
        return new ResultPage<>(responses, pageNumber, ATTRACTION_SIZE, attractionQueryService.getPageTotalCnt(condition));
    }

}

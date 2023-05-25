package com.ssafy.faraway.domain.attraction.controller;

import com.ssafy.faraway.common.domain.ResultPage;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import com.ssafy.faraway.domain.attraction.entity.Sido;
import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.service.AttractionLikeService;
import com.ssafy.faraway.domain.attraction.service.AttractionQueryService;
import com.ssafy.faraway.domain.attraction.service.AttractionService;
import com.ssafy.faraway.domain.attraction.service.dto.SaveAttractionLikeDto;
import com.ssafy.faraway.domain.member.service.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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
    private final AttractionLikeService attractionLikeService;
    private final JwtService jwtService;

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
            @RequestParam(required = false) Integer sidoCode,
            @RequestParam(required = false) Integer gugunCode,
            @RequestParam(required = false) Integer contentTypeId,
            @RequestParam(defaultValue = "0") Long memberId,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String address) {
        AttractionSearchCondition condition = AttractionSearchCondition.builder()
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .contentTypeId(contentTypeId)
                .title(title)
                .address(address)
                .build();
        log.debug("condition: {}", condition);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, ATTRACTION_SIZE);
        List<AttractionResponse> responses = attractionQueryService.searchByCondition(condition, memberId, pageRequest);
        log.debug("responses size: {}", responses.size());
        return new ResultPage<>(responses, pageNumber, ATTRACTION_SIZE, attractionQueryService.getPageTotalCnt(condition));
    }

    @PostMapping("/{contentId}/like")
    public Long saveAttractionLike(@PathVariable Integer contentId) {
        SaveAttractionLikeDto dto = SaveAttractionLikeDto.builder()
                .contentId(contentId)
                .memberId(jwtService.getMemberId())
                .build();
        return attractionLikeService.save(dto);
    }

    @DeleteMapping("/like/{likeId}")
    public Long deleteAttractionLike(@PathVariable Long likeId) {
        return attractionLikeService.delete(likeId);
    }
}

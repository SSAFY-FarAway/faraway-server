package com.ssafy.faraway.domain.attraction.controller;

import com.ssafy.faraway.domain.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.controller.dto.AttractionResponse;
import com.ssafy.faraway.domain.attraction.controller.dto.GugunResponse;
import com.ssafy.faraway.domain.attraction.entity.Sido;
import com.ssafy.faraway.domain.attraction.service.AttractionQueryService;
import com.ssafy.faraway.domain.attraction.service.AttractionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/attraction")
@CrossOrigin(originPatterns = "*")
@Api(tags = "attraction")
public class AttractionController {
    private final AttractionService attractionService;
    private final AttractionQueryService attractionQueryService;
    @GetMapping("/sido")
    public List<Sido> findAllSido() throws IOException {
        List<Sido> list = attractionService.findAllSido();
        if(list == null || list.size() == 0) {
            throw new IOException();
        }
        return list;
    }

    @GetMapping("/gugun/{sidoCode}")
    public List<GugunResponse> searchGugunBySidoCode(@PathVariable int sidoCode) throws IOException{
        List<GugunResponse> list = attractionQueryService.searchGugunBySidoCode(sidoCode);
        if(list == null || list.size() == 0) {
            throw new IOException();
        }
        return list;

    }

    @GetMapping
    public List<AttractionResponse> searchByCondition(@RequestBody AttractionSearchCondition condition) throws IOException{
        List<AttractionResponse> list = attractionQueryService.searchByCondition(condition);
        if(list == null || list.size() == 0) {
            throw new IOException();
        }
        return list;
    }

}

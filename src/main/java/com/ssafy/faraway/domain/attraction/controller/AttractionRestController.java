package com.ssafy.faraway.domain.attraction.controller;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.domain.attraction.dto.req.AttractionSearchCondition;
import com.ssafy.faraway.domain.attraction.dto.res.AttractionResponse;
import com.ssafy.faraway.domain.attraction.dto.res.GugunResponse;
import com.ssafy.faraway.domain.attraction.entity.Sido;
import com.ssafy.faraway.domain.attraction.service.AttractionQueryService;
import com.ssafy.faraway.domain.attraction.service.AttractionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/attraction")
@Api(tags = "attraction")
public class AttractionRestController {
    private final AttractionService attractionService;
    private final AttractionQueryService attractionQueryService;
    @GetMapping("/sido")
    public List<Sido> findAllSido() throws IOException {
        List<Sido> list = null;
        list = attractionService.findAllSido();
        if(list == null || list.size() == 0) {
//                throw new CustomException()
            throw new IOException();
        }
        return list;
    }

    @GetMapping("/gugun/{sidoCode}")
    public List<GugunResponse> searchGugunBySidoCode(@PathVariable int sidoCode) throws IOException{
        List<GugunResponse> list = null;
        list = attractionQueryService.searchGugunBySidoCode(sidoCode);
        if(list == null || list.size() == 0) {
            throw new IOException();
        }
        return list;
    }

    @GetMapping("")
    public List<AttractionResponse> searchByCondition(@RequestBody AttractionSearchCondition condition) throws IOException{
        List<AttractionResponse> list = null;
        list = attractionQueryService.searchByCondition(condition);
        if(list == null || list.size() == 0) {
            throw new IOException();
        }
        return list;
    }



}

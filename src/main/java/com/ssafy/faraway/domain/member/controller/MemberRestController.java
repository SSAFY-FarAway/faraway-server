package com.ssafy.faraway.domain.member.controller;

import com.ssafy.faraway.domain.member.dto.req.SaveMemberRequest;
import com.ssafy.faraway.domain.member.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.service.MemberQueryService;
import com.ssafy.faraway.domain.member.service.MemberService;
import com.ssafy.faraway.domain.post.dto.res.ListPostResponse;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
@Api(tags="member")
//@API(tags = "member")
public class MemberRestController {
    private final MemberService memberService;
    private final MemberQueryService memberQueryService;


    // 회원 상세조회
    @GetMapping("/{memberId}")
    public MemberResponse searchMember(@PathVariable Long memberId){
        MemberResponse response = memberQueryService.searchById(memberId);
        log.debug("response {}",response);
        return response;
    }

    @GetMapping
    public ResultPage<List<ListMemberResponse>> searchMember(
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<ListMemberResponse> responses = memberQueryService.searchAll(pageRequest);
        log.debug("responses: {}", responses);
        for (ListMemberResponse response : responses) {
            log.debug("response: {}", response);
        }
        return new ResultPage<>(responses,pageNumber,10);
    }


    // 회원 가입
    @PostMapping("/sign-up")
    public ResponseEntity<?> save(@RequestBody @Valid final SaveMemberRequest saveMemberRequest) {
        try {
            System.out.println("--------------@@@-----------");
            Long id = memberService.saveMember(saveMemberRequest);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

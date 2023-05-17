package com.ssafy.faraway.domain.member.controller;

import com.ssafy.faraway.domain.member.dto.req.*;
import com.ssafy.faraway.domain.member.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.service.MemberQueryService;
import com.ssafy.faraway.domain.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
@Api(tags="member")
public class MemberController {
    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginMemberRequest request, HttpSession session) {
        try {
            LoginMemberResponse response = memberQueryService.login(request);
            if(response != null){
                session.setAttribute("loginMember", response);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    // 회원 상세조회
    @GetMapping("/{memberId}")
    public MemberResponse searchMember(@PathVariable Long memberId){
        MemberResponse response = memberQueryService.searchById(memberId);
        log.debug("response {}",response);
        return response;
    }

    // 아이디 중복 검사
    @GetMapping("/check/{loginId}") //countByLoginId
    public ResponseEntity<?> checkLoginId(@PathVariable("loginId") String loginId) {
        int cnt = 0;
        if(memberService.checkLoginId(loginId)){
            cnt = 1;
        }
        return new ResponseEntity<>(cnt + "", HttpStatus.OK);
    }

    // 입력한 비밀번호가 맞는지 확인
    @PostMapping("/check")
    public ResponseEntity<?> checkLoginPwd(@RequestBody @Valid final CheckLoginPwdRequest request) {
        if(memberQueryService.checkLoginPwd(request)){
            return new ResponseEntity<>("비밀번호가 맞습니다", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("비밀번호가 틀립니다", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public MemberController.ResultPage<List<ListMemberResponse>> searchMember(
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<ListMemberResponse> responses = memberQueryService.searchAll(pageRequest);
        log.debug("responses: {}", responses);
        for (ListMemberResponse response : responses) {
            log.debug("response: {}", response);
        }
        return new MemberController.ResultPage<>(responses,pageNumber,10);
    }


    // 회원 가입x
    @PostMapping("/sign-up")
    public ResponseEntity<?> save(@RequestBody @Valid final SaveMemberRequest saveMemberRequest) {
        try {
            Long id = memberService.saveMember(saveMemberRequest);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        LoginMemberResponse dto = (LoginMemberResponse)session.getAttribute("loginMember");
        if(dto == null){
            return new ResponseEntity<>("로그인 정보가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공.", HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<?> updateLoginPwd(@RequestBody @Valid UpdateLoginPwdRequest request) {
        if(memberService.updateLoginPwd(request) == -1){
//                throw new CustomException(ErrorCode.BAD_REQUEST);
            return new ResponseEntity<>("비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateMemberRequest request) {

        try {
            memberService.updateMember(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("변경 실패",HttpStatus.BAD_REQUEST);
        }
    }

    // 아이디 찾기
    @PostMapping("/login-id")
    public ResponseEntity<?> searchLoginId(@RequestBody @Valid final FindLoginIdRequest request){
        try {
            String loginId = memberQueryService.searchLoginId(request);
            return new ResponseEntity<>(loginId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("정확한 정보를 입력해주세요",HttpStatus.BAD_REQUEST);
        }
    }

    //비밀번호 초기화
    @PostMapping("/login-pwd")
    public ResponseEntity<?> resetLoginPwd(@RequestBody @Valid final ResetLoginPwdRequest request){
        try {
            if(memberService.resetLoginPwd(request) == -1L){
                return new ResponseEntity<>("정확한 정보를 입력해주세요",HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>("00000000", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("정확한 정보를 입력해주세요",HttpStatus.BAD_REQUEST);
        }
    }

    //회원탈퇴
    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestBody @Valid final DeleteMemberRequest request){
        try {
            if(memberService.deleteMember(request) == -1L){
                return new ResponseEntity<>("비밀번호가 틀렸습니다.",HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>("회원탈퇴가 정상 처리 되었습니다", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

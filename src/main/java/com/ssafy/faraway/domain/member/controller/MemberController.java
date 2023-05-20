package com.ssafy.faraway.domain.member.controller;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.member.controller.dto.req.*;
import com.ssafy.faraway.domain.member.controller.dto.res.ListMemberResponse;
import com.ssafy.faraway.domain.member.controller.dto.res.LoginMemberResponse;
import com.ssafy.faraway.domain.member.controller.dto.res.MemberResponse;
import com.ssafy.faraway.domain.member.service.JwtService;
import com.ssafy.faraway.domain.member.service.MemberQueryService;
import com.ssafy.faraway.domain.member.service.MemberService;
import com.ssafy.faraway.domain.member.service.dto.CheckLoginPwdDto;
import com.ssafy.faraway.domain.member.service.dto.LoginMemberDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
@Api(tags="member")
@CrossOrigin(originPatterns="*")
public class MemberController {
    private final MemberService memberService;
    private final MemberQueryService memberQueryService;
    private final JwtService jwtService;

    // 로그인
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody @Valid LoginMemberRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        // dto 변환
        LoginMemberDto dto = LoginMemberDto.builder()
                .loginId(request.getLoginId())
                .loginPwd(request.getLoginPwd())
                .build();
        // 응답
        LoginMemberResponse response = memberQueryService.login(dto);

        if(response != null){
            String accessToken = jwtService.createAccessToken("memberId",response.getId());
            String refreshToken = jwtService.createRefreshToken("memberId",response.getId());
            memberService.saveRefreshToken(response.getId(), refreshToken);
            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);
            return resultMap;
        }else{
            // 유효하지 않은 아이디, 비밀번호
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
        }
    }

    //회원정보 얻기(토큰 기반)
    @GetMapping("/info/{memberId}")
    public Map<String, Object> getInfo(
            @PathVariable("memberId") @ApiParam(value = "인증할 회원의 아이디.", required = true) Long memberId,
            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();

        if (jwtService.checkToken(request.getHeader("access-token"))) {
//			로그인 사용자 정보.
            LoginMemberResponse response = memberQueryService.searchLoginMemberById(memberId);
            resultMap.put("loginMember", response);
        } else {
            // token이 유효하지 않음
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
        }
        return resultMap;
    }

    // 회원 상세조회
    @GetMapping("mypage/{memberId}")
    public MemberResponse searchMember(@PathVariable Long memberId){
        MemberResponse response = memberQueryService.searchById(memberId);
        log.debug("response {}",response);
        if(response == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
        }
        return response;
    }

    // 아이디 중복 검사
    @GetMapping("/check/{loginId}") //countByLoginId
    public int checkLoginId(@PathVariable("loginId") String loginId) {
        int cnt = 0;
        if(memberService.checkLoginId(loginId)){
            cnt = 1;
        }else{
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
        }
        return cnt;
    }

    // 유저가 입력한 비밀번호가 맞는지 확인
    @PostMapping("/check")
    public String checkLoginPwd(@RequestBody @Valid final CheckLoginPwdRequest request) {
        CheckLoginPwdDto dto = CheckLoginPwdDto.builder()
                .id(request.getId())
                .loginPwd(request.getLoginPwd())
                .build();

        if(memberQueryService.checkLoginPwd(dto)){

            return "비밀번호가 맞습니다";
        }else{
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
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


    // 회원 가입
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
    @GetMapping("/logout/{memberId}")
    public ResponseEntity<?> logout(@PathVariable("memberId") Long memberId) {
        Map<String, Object> resultMap = new HashMap<>();
        String token = memberQueryService.searchRefreshToken(memberId);
        if(token == null){
            return new ResponseEntity<>("로그인 정보가 없습니다.", HttpStatus.UNAUTHORIZED);
        }
        memberService.deleteRefreshToken(memberId);
        return new ResponseEntity<>("로그아웃 성공.", HttpStatus.OK);
    }

    // 비밀번호 변경
    @PutMapping("/password")
    public ResponseEntity<?> updateLoginPwd(@RequestBody @Valid UpdateLoginPwdRequest request) {
        if(memberService.updateLoginPwd(request) == -1){
//                throw new CustomException(ErrorCode.BAD_REQUEST);
            return new ResponseEntity<>("비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
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
    @DeleteMapping
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

    //Access Token 재발급 -> access token 만료 시 재발급
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody LoginMemberResponse response, HttpServletRequest request) {
        log.debug("error 발생 시점 체크");
        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refresh-token");
//        logger.debug("token : {}, memberDto : {}", token, memberDto);
        if (jwtService.checkToken(token)) {
            if (token.equals(memberQueryService.searchRefreshToken(response.getId()))) {
                String accessToken = jwtService.createAccessToken("memberId",response.getId());
                resultMap.put("access-token", accessToken);
            }
        } else {
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }


    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

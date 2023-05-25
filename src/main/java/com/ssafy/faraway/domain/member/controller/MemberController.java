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
import com.ssafy.faraway.domain.member.service.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
@Api(tags="member")
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
//            jwtService.getUserId();
            LoginMemberResponse response = memberQueryService.searchLoginMemberById(memberId);
            resultMap.put("loginMember", response);
        } else {
            // token이 유효하지 않음
            throw new CustomException(ErrorCode.AT_UNAUTHORIZED_ERROR);
        }
        return resultMap;
    }

    // 회원 상세조회
    @GetMapping("/mypage")
    public MemberResponse searchMember(){
        MemberResponse response = memberQueryService.searchById(jwtService.getMemberId());
        log.debug("response {}",response);
//        System.out.println("@@@@@@@@memberID : " + jwtService.getMemberId());
        if(response == null){
            throw new CustomException(ErrorCode.LOGIN_UNAUTHORIZED_ERROR);
        }
        return response;
    }

    // 아이디 중복 검사
    @GetMapping("/check/login-id/{loginId}") //countByLoginId
    public int checkLoginId(@PathVariable("loginId") String loginId) {
        int cnt = 0;
        if(memberService.checkLoginId(loginId)){
            cnt = 1;
        }
        if(cnt == 0){
            return cnt;
        }else{
            throw new CustomException(ErrorCode.LOGIN_ID_CONFLICT_ERROR);
        }
    }

    @GetMapping("/check/email/{email}") //countByEmail
    public int checkEmail(@PathVariable("email") String email) {
        int cnt = 0;
        if(memberService.checkEmail(email)){
            cnt = 1;
        }
        if(cnt == 0){
            return cnt;
        }else{
            throw new CustomException(ErrorCode.EMAIL_CONFLICT_ERROR);
        }
    }

    // 유저가 입력한 비밀번호가 맞는지 확인
    @PostMapping("/check/password")
    public String checkLoginPwd(@RequestBody @Valid final CheckLoginPwdRequest request) {
        CheckLoginPwdDto dto = CheckLoginPwdDto.builder()
                .id(request.getId())
                .loginPwd(request.getLoginPwd())
                .build();

        if(memberQueryService.checkLoginPwd(dto)){
            return "비밀번호가 맞습니다";
        }else{
            throw new CustomException(ErrorCode.PASSWORD_UNAUTHORIZED_ERROR);
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
    public Long save(@RequestBody @Valid final SaveMemberRequest request) {
        try {
            SaveMemberDto dto = SaveMemberDto.builder()
                    .loginId(request.getLoginId())
                    .loginPwd(request.getLoginPwd())
                    .lastName(request.getLastName())
                    .firstName(request.getFirstName())
                    .birth(request.getBirth())
                    .email(request.getEmail())
                    .zipcode(request.getZipcode())
                    .mainAddress(request.getMainAddress())
                    .subAddress(request.getSubAddress())
                    .build();
            Long id = memberService.saveMember(dto);
            return id;
        } catch (Exception e) {
            // INSERT 도중 에러
            e.printStackTrace();
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
    }

    //로그아웃
    @GetMapping("/logout/{memberId}")
    public String logout(@PathVariable("memberId") Long memberId) {
        String token = memberQueryService.searchRefreshToken(memberId);
        if(token == null){
            throw new CustomException(ErrorCode.RT_UNAUTHORIZED_ERROR);
        }
        memberService.deleteRefreshToken(memberId);
        return "로그아웃 성공.";
    }

    // 비밀번호 변경
    @PutMapping("/password")
    public String updateLoginPwd(@RequestBody @Valid UpdateLoginPwdRequest request) {
        UpdateLoginPwdDto dto = UpdateLoginPwdDto.builder()
                .id(request.getId())
                .currentLoginPwd(request.getCurrentLoginPwd())
                .newLoginPwd(request.getNewLoginPwd())
                .build();

        if(memberService.updateLoginPwd(dto) == -1){
                //비밀번호가 일치하지 않음
                throw new CustomException(ErrorCode.PASSWORD_UNAUTHORIZED_ERROR);
        }
        return "비밀번호가 성공적으로 변경 되었습니다.";
    }
    
    // 회원정보 수정
    @PutMapping
    public Long update(@RequestBody @Valid UpdateMemberRequest request) {
        UpdateMemberDto dto = UpdateMemberDto.builder()
                .id(request.getId())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .birth(request.getBirth())
                .email(request.getEmail())
                .zipcode(request.getZipcode())
                .mainAddress(request.getMainAddress())
                .subAddress(request.getSubAddress())
                .build();
        return memberService.updateMember(dto);
    }

    // 아이디 찾기
    @GetMapping("/login-id")
    public String searchLoginId(@RequestParam("birth") @NotEmpty(message = "birth's size must not be 6") @Size(min=6, max=6) String birth,
                                           @RequestParam("email")  @NotEmpty(message = "email must not be empty") @Email String email){

        FindLoginIdDto dto = FindLoginIdDto.builder()
                .birth(birth)
                .email(email)
                .build();
        String loginId = memberQueryService.searchLoginId(dto);
        if(loginId == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
        }
        return loginId;
    }

    //비밀번호 초기화
    @PostMapping("/login-pwd")
    public String resetLoginPwd(@RequestBody @Valid final ResetLoginPwdRequest request){
        ResetLoginPwdDto dto = ResetLoginPwdDto.builder()
                .loginId(request.getLoginId())
                .email(request.getEmail())
                .birth(request.getBirth())
                .build();
        if(memberService.resetLoginPwd(dto) == -1L){
            // 이메일, 생일, 로그인아이디중 안맞는것이 존재함
            throw new CustomException(ErrorCode.UNAUTHORIZED_ERROR);
        }else{
            return "00000000"; // 비밀번호 초기화
        }

    }

    //회원탈퇴
    @PostMapping
    public String delete(@RequestBody @Valid final DeleteMemberRequest request){
        DeleteMemberDto dto = DeleteMemberDto.builder()
                .id(request.getId())
                .loginPwd(request.getLoginPwd())
                .build();
        if(memberService.deleteMember(dto) == -1L){
            throw new CustomException(ErrorCode.PASSWORD_UNAUTHORIZED_ERROR);
        }else{
            return "회원탈퇴가 정상 처리 되었습니다";
        }
    }

    //Access Token 재발급 -> access token 만료 시 재발급
    // RequestParm을 쓰기 때문에 body에 붙여서 보내면 안됨.
    @PostMapping("/refresh")
    public Map<String, Object> refreshToken(@RequestParam("memberId") String memberId, HttpServletRequest request) {
        log.debug("error 발생 시점 체크");
        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refresh-token");
        long id = Long.parseLong(memberId);
//        jwtService.getMemberId();
//        logger.debug("token : {}, memberDto : {}", token, memberDto);
        if (jwtService.checkToken(token)) {
            if (token.equals(memberQueryService.searchRefreshToken(id))) {
                String accessToken = jwtService.createAccessToken("memberId",id);
                resultMap.put("access-token", accessToken);
                System.out.println(accessToken);
            }
        } else {
            throw new CustomException(ErrorCode.RT_UNAUTHORIZED_ERROR);
        }
        return resultMap;
    }


    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

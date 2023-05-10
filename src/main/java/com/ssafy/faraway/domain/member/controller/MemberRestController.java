package com.ssafy.faraway.domain.member.controller;

import com.ssafy.faraway.domain.member.dto.SaveMemberRequest;
import com.ssafy.faraway.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
//@API(tags = "member")
public class MemberRestController {
    private final MemberService memberService;

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
}

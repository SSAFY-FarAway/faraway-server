package com.ssafy.faraway.domain.post.controller;

import com.ssafy.faraway.domain.post.dto.PostResponse;
import com.ssafy.faraway.domain.post.dto.SavePostRequest;
import com.ssafy.faraway.domain.post.service.PostService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@Api(tags = "post")
public class PostController {
    private final PostService postService;
    @RequestMapping(value = "/", method = POST)
    public Long postSave(@Valid @RequestBody SavePostRequest savePostRequest) {
        // TODO: 최영환 2023-05-10 회원 구현되면 변경해야함
        Long memberId = 1L;
        Long postId = postService.save(savePostRequest, memberId);
        log.debug("postId {}", postId);
        return postId;
    }

    @RequestMapping(value = "/{postId}", method = GET)
    public PostResponse searchPost(@PathVariable Long postId) {
        PostResponse response = postService.searchById(postId);
        log.debug("response {}", response);
        return response;
    }
}

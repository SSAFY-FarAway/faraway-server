package com.ssafy.faraway.domain.post.controller;

import com.ssafy.faraway.domain.post.dto.req.PostSearchCondition;
import com.ssafy.faraway.domain.post.dto.req.SavePostCommentRequest;
import com.ssafy.faraway.domain.post.dto.req.SavePostRequest;
import com.ssafy.faraway.domain.post.dto.req.UpdatePostRequest;
import com.ssafy.faraway.domain.post.dto.res.ListPostResponse;
import com.ssafy.faraway.domain.post.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.service.PostCommentService;
import com.ssafy.faraway.domain.post.service.PostQueryService;
import com.ssafy.faraway.domain.post.service.PostService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@Api(tags = "post")
public class PostController {
    private final PostService postService;
    private final PostQueryService postQueryService;
    private final PostCommentService postCommentService;
    @PostMapping
    public Long postSave(@Valid @RequestBody SavePostRequest savePostRequest) {
        // TODO: 최영환 2023-05-10 회원 구현되면 변경해야함
        Long memberId = 1L;
        Long postId = postService.save(savePostRequest, memberId);
        log.debug("postId {}", postId);
        return postId;
    }

    @GetMapping("/{postId}")
    public PostResponse searchPost(@PathVariable Long postId) {
        PostResponse response = postQueryService.searchById(postId);
        log.debug("response {}", response);
        return response;
    }

    @PutMapping("/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        return postService.update(postId, request);
    }

    @GetMapping
    public ResultPage<List<ListPostResponse>> searchPost(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PostSearchCondition condition =PostSearchCondition.builder()
                .title(title)
                .content(content)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<ListPostResponse> responses = postQueryService.searchByCondition(condition, pageRequest);
        log.debug("responses: {}", responses);
        for (ListPostResponse response : responses) {
            log.debug("response: {}", response);
        }
        return new ResultPage<>(responses, pageNumber, 10);
    }
    
    @PostMapping("/{postId}/comment")
    public Long savePostComment(@PathVariable Long postId, @Valid @RequestBody SavePostCommentRequest request) {
        // TODO: 최영환 2023-05-11 회원 구현되면 변경해야함
        Long memberId = 1L;
        Long postCommentId = postCommentService.save(postId, memberId, request);
        log.debug("postCommentId: {}", postCommentId);
        return postCommentId;
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

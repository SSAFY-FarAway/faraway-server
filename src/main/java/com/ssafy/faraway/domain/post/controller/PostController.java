package com.ssafy.faraway.domain.post.controller;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.domain.post.dto.req.UpdatePostCommentRequest;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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
    private final FileStore fileStore;

    @PostMapping
    public Long savePost(@Valid @RequestPart SavePostRequest savePostRequest,
                         @RequestPart(name = "files") List<MultipartFile> files) throws IOException {
        // TODO: 최영환 2023-05-10 회원 구현되면 변경해야함
        Long memberId = 1L;
        List<UploadFile> uploadFiles = fileStore.storeFiles(files);
        return postService.save(savePostRequest, memberId, uploadFiles);
    }

    @GetMapping("/{postId}")
    public PostResponse searchPost(@PathVariable Long postId) {
        return postQueryService.searchById(postId);
    }

    @PutMapping("/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        return postService.update(postId, request);
    }

    @GetMapping
    public ResultPage<List<ListPostResponse>> searchPosts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PostSearchCondition condition = PostSearchCondition.builder()
                .title(title)
                .content(content)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<ListPostResponse> responses = postQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, 10);
    }

    @DeleteMapping("/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.delete(postId);
    }

    @PostMapping("/{postId}/comment")
    public Long savePostComment(@PathVariable Long postId, @Valid @RequestBody SavePostCommentRequest request) {
        // TODO: 최영환 2023-05-11 회원 구현되면 변경해야함
        Long memberId = 1L;
        return postCommentService.save(postId, memberId, request);
    }

    @PutMapping("/comment/{commentId}")
    public Long updatePostComment(@PathVariable Long commentId, @Valid @RequestBody UpdatePostCommentRequest request) {
        return postCommentService.update(commentId, request);
    }

    @DeleteMapping("/comment/{commentId}")
    public Long deletePostComment(@PathVariable Long commentId) {
        return postCommentService.delete(commentId);
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}

package com.ssafy.faraway.domain.post.controller;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.common.domain.ResultPage;
import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.common.util.FileExtFilter;
import com.ssafy.faraway.domain.post.controller.dto.req.UpdatePostCommentRequest;
import com.ssafy.faraway.domain.post.repository.dto.PostSearchCondition;
import com.ssafy.faraway.domain.post.controller.dto.req.SavePostCommentRequest;
import com.ssafy.faraway.domain.post.controller.dto.req.SavePostRequest;
import com.ssafy.faraway.domain.post.controller.dto.req.UpdatePostRequest;
import com.ssafy.faraway.domain.post.controller.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.controller.dto.res.DetailPostResponse;
import com.ssafy.faraway.domain.post.service.PostCommentService;
import com.ssafy.faraway.domain.post.service.PostQueryService;
import com.ssafy.faraway.domain.post.service.PostService;
import com.ssafy.faraway.domain.post.service.dto.SavePostDto;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostCommentDto;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.faraway.common.util.SizeConstants.PAGE_SIZE;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@CrossOrigin(originPatterns = "*")
@Api(tags = "post")
public class PostController {
    private final PostService postService;
    private final PostQueryService postQueryService;
    private final PostCommentService postCommentService;
    private final FileStore fileStore;
    private final FileExtFilter fileExtFilter;

    @PostMapping
    public Long savePost(@Valid @RequestPart SavePostRequest request,
                         @RequestPart(required = false) List<MultipartFile> files) throws IOException {
        // TODO: 최영환 2023-05-10 회원 구현되면 변경해야함
        Long memberId = 1L;
        List<UploadFile> uploadFiles = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            fileExtFilter.badFileFilter(files);
            uploadFiles = fileStore.storeFiles(files);
        }

        SavePostDto dto = SavePostDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .categoryId(request.getCategoryId())
                .build();

        return postService.save(dto, memberId, uploadFiles);
    }

    @GetMapping("/{postId}")
    public DetailPostResponse searchPost(@PathVariable Long postId) {
        // TODO: 2023-05-19 로그인 처리 후 수정해야함. 조회수 증가 처리용(본인의 글 조회 시 조회수 증가 X)
        Long loginId = 1L;
        DetailPostResponse response = postQueryService.searchById(postId, loginId);
        if (response == null) {
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestPart UpdatePostRequest request,
                           @RequestPart(required = false) List<MultipartFile> files) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            fileExtFilter.badFileFilter(files);
            uploadFiles = fileStore.storeFiles(files);
        }
        UpdatePostDto dto = UpdatePostDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .deleteAttachmentIds(request.getDeleteAttachmentIds())
                .build();

        return postService.update(postId, dto, uploadFiles);
    }

    @GetMapping
    public ResultPage<List<PostResponse>> searchPosts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        PostSearchCondition condition = PostSearchCondition.builder()
                .title(title)
                .content(content)
                .categoryId(categoryId)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);
        List<PostResponse> responses = postQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, PAGE_SIZE, postQueryService.getPageTotalCnt(condition));
    }

    @DeleteMapping("/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.delete(postId);
    }

    @PostMapping("/{postId}/comment")
    public Long savePostComment(@PathVariable Long postId,
                                @Valid @RequestBody SavePostCommentRequest request) {
        // TODO: 최영환 2023-05-11 회원 구현되면 변경해야함
        Long memberId = 1L;
        return postCommentService.save(postId, memberId, request);
    }

    @PutMapping("/comment/{commentId}")
    public Long updatePostComment(@PathVariable Long commentId,
                                  @Valid @RequestBody UpdatePostCommentRequest request) {
        UpdatePostCommentDto dto = UpdatePostCommentDto.builder()
                .content(request.getContent())
                .build();
        return postCommentService.update(commentId, dto);
    }

    @DeleteMapping("/comment/{commentId}")
    public Long deletePostComment(@PathVariable Long commentId) {
        return postCommentService.delete(commentId);
    }
}

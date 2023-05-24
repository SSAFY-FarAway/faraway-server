package com.ssafy.faraway.domain.post.controller;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.common.domain.ResultPage;
import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.common.util.FileExtFilter;
import com.ssafy.faraway.domain.member.service.JwtService;
import com.ssafy.faraway.domain.post.controller.dto.req.*;
import com.ssafy.faraway.domain.post.repository.dto.PostSearchCondition;
import com.ssafy.faraway.domain.post.controller.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.controller.dto.res.DetailPostResponse;
import com.ssafy.faraway.domain.post.service.PostCommentService;
import com.ssafy.faraway.domain.post.service.PostLikeService;
import com.ssafy.faraway.domain.post.service.PostQueryService;
import com.ssafy.faraway.domain.post.service.PostService;
import com.ssafy.faraway.domain.post.service.dto.*;
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
@Api(tags = "post")
public class PostController {
    private final PostService postService;
    private final PostQueryService postQueryService;
    private final PostCommentService postCommentService;
    private final PostLikeService postLikeService;
    private final FileStore fileStore;
    private final FileExtFilter fileExtFilter;
    private final JwtService jwtService;

    @PostMapping
    public Long savePost(@Valid @RequestPart SavePostRequest request,
                         @RequestPart(required = false) List<MultipartFile> files) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            fileExtFilter.badFileFilter(files);
            uploadFiles = fileStore.storeFiles(files);
        }

        SavePostDto dto = SavePostDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .memberId(jwtService.getMemberId())
                .categoryId(request.getCategoryId())
                .uploadFiles(uploadFiles)
                .build();

        return postService.save(dto);
    }

    @GetMapping
    public ResultPage<List<PostResponse>> searchPosts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "0") Long memberId,
            @RequestParam(defaultValue = "1") Integer orderType,
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "1") Integer pageNumber) {
        PostSearchCondition condition = PostSearchCondition.builder()
                .title(title)
                .content(content)
                .categoryId(categoryId)
                .memberId(memberId)
                .orderType(orderType)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);
        List<PostResponse> responses = postQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, PAGE_SIZE, postQueryService.getPageTotalCnt(condition));
    }

    @GetMapping("/{postId}")
    public DetailPostResponse searchPost(@PathVariable Long postId) {
        DetailPostResponse response = postQueryService.searchById(postId, jwtService.getMemberId());

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
                .postId(postId)
                .deleteAttachmentIds(request.getDeleteAttachmentIds())
                .uploadFiles(uploadFiles)
                .build();

        return postService.update(dto);
    }

    @DeleteMapping("/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.delete(postId);
    }

    @PostMapping("/{postId}/comment")
    public Long savePostComment(@PathVariable Long postId,
                                @Valid @RequestBody SavePostCommentRequest request) {
        SavePostCommentDto dto = SavePostCommentDto.builder()
                .postId(postId)
                .memberId(jwtService.getMemberId())
                .content(request.getContent())
                .build();
        return postCommentService.save(dto);
    }

    @PutMapping("/comment/{commentId}")
    public Long updatePostComment(@PathVariable Long commentId,
                                  @Valid @RequestBody UpdatePostCommentRequest request) {
        UpdatePostCommentDto dto = UpdatePostCommentDto.builder()
                .commentId(commentId)
                .content(request.getContent())
                .build();
        return postCommentService.update(dto);
    }

    @DeleteMapping("/comment/{commentId}")
    public Long deletePostComment(@PathVariable Long commentId) {
        return postCommentService.delete(commentId);
    }

    @PostMapping("/{postId}/like")
    public Long savePostLike(@PathVariable Long postId) {
        SavePostLikeDto dto = SavePostLikeDto.builder()
                .postId(postId)
                .memberId(jwtService.getMemberId())
                .build();
        return postLikeService.save(dto);
    }

    @DeleteMapping("/like/{likeId}")
    public Long deletePostLike(@PathVariable Long likeId) {
        return postLikeService.delete(likeId);
    }
}

package com.ssafy.faraway.domain.post.service.impl;

import com.ssafy.faraway.domain.post.repository.PostLikeQueryRepository;
import com.ssafy.faraway.domain.post.repository.dto.PostSearchCondition;
import com.ssafy.faraway.domain.post.controller.dto.res.AttachmentResponse;
import com.ssafy.faraway.domain.post.controller.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.controller.dto.res.PostCommentResponse;
import com.ssafy.faraway.domain.post.controller.dto.res.DetailPostResponse;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.PostQueryRepository;
import com.ssafy.faraway.domain.post.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.faraway.common.util.SizeConstants.PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {
    private final PostQueryRepository postQueryRepository;
    private final PostLikeQueryRepository postLikeQueryRepository;

    @Transactional
    @Override
    public DetailPostResponse searchById(Long postId, Long memberId) {
        Post post = postQueryRepository.searchById(postId);
        if (!post.getMember().getId().equals(memberId)) {
            post.increaseHit();
        }
        List<PostCommentResponse> commentResponses = getCommentResponses(post);
        List<AttachmentResponse> attachmentResponses = getAttachmentResponses(post);
        return DetailPostResponse.builder()
                .id(post.getId())
                .memberId(post.getMember().getId())
                .loginId(post.getMember().getLoginId())
                .categoryName(post.getCategory().getCategoryName())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .likeCnt(post.getLikes().size())
                .likeId(postLikeQueryRepository.searchLikeId(memberId, postId))
                .postCommentResponses(commentResponses)
                .attachmentResponses(attachmentResponses)
                .createdDate(post.getCreatedDate())
                .build();
    }

    @Override
    public List<PostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable) {
        return postQueryRepository.searchByCondition(condition, pageable);
    }

    @Override
    public int getPageTotalCnt(PostSearchCondition condition) {
        return ((postQueryRepository.getPageTotalCnt(condition) - 1) / PAGE_SIZE) + 1;
    }

    private List<PostCommentResponse> getCommentResponses(Post post) {
        return post.getPostComments().stream().map(postComment -> PostCommentResponse.builder()
                .id(postComment.getId())
                .postId(postComment.getPost().getId())
                .loginId(post.getMember().getLoginId())
                .memberId(postComment.getMember().getId())
                .content(postComment.getContent())
                .createdDate(postComment.getCreatedDate())
                .build()).collect(Collectors.toList());
    }

    private List<AttachmentResponse> getAttachmentResponses(Post post) {
        return post.getAttachments().stream()
                .map(attachment -> AttachmentResponse.builder()
                        .id(attachment.getId())
                        .fileName(attachment.getUploadFile().getUploadFileName())
                        .createdDate(attachment.getCreatedDate()).build()
                )
                .collect(Collectors.toList());
    }
}

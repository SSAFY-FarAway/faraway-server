package com.ssafy.faraway.domain.post.service.impl;

import com.ssafy.faraway.domain.post.dto.req.PostSearchCondition;
import com.ssafy.faraway.domain.post.dto.res.AttachmentResponse;
import com.ssafy.faraway.domain.post.dto.res.ListPostResponse;
import com.ssafy.faraway.domain.post.dto.res.PostCommentResponse;
import com.ssafy.faraway.domain.post.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.entity.Attachment;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.PostQueryRepository;
import com.ssafy.faraway.domain.post.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {
    private final PostQueryRepository postQueryRepository;

    @Transactional
    @Override
    public PostResponse searchById(Long postId) {
        Post post = postQueryRepository.searchById(postId);
        post.updateHit();
        List<PostCommentResponse> commentResponses = getCommentResponses(post);
        List<AttachmentResponse> attachmentResponses = getAttachmentResponses(post);
        return PostResponse.builder()
                .id(post.getId())
                .memberId(post.getMember().getId())
                .loginId(post.getMember().getLoginId())
                .categoryName(post.getCategory().getCategoryName())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .postCommentResponses(commentResponses)
                .attachmentResponses(attachmentResponses)
                .createdDate(post.getCreatedDate())
                .build();
    }

    @Override
    public List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable) {
        return postQueryRepository.searchByCondition(condition, pageable);
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
        return post.getAttachments().stream().map(attachment -> AttachmentResponse.builder()
                .id(attachment.getId())
                .fileName(attachment.getUploadFile().getUploadFileName())
                .createdDate(attachment.getCreatedDate()).build()
        ).collect(Collectors.toList());
    }
}

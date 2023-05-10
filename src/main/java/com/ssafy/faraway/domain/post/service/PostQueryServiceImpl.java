package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.req.PostSearchCondition;
import com.ssafy.faraway.domain.post.dto.res.ListPostResponse;
import com.ssafy.faraway.domain.post.dto.res.PostCommentResponse;
import com.ssafy.faraway.domain.post.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {
    private final PostQueryRepository postQueryRepository;
    @Override
    public PostResponse searchById(Long postId) {
        Post post = postQueryRepository.searchById(postId);
        post.updateHit();
        List<PostCommentResponse> commentResponses = getCommentResponses(post);
        return PostResponse.builder()
                .id(post.getId())
                .memberId(post.getMember().getId())
                .loginId(post.getMember().getLoginId())
                .categoryName(post.getCategory().getCategoryName())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .postCommentResponses(commentResponses)
                .createdDate(post.getCreatedDate())
                .build();
    }

    @Override
    public List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable) {
        return postQueryRepository.searchByCondition(condition, pageable);
    }

    private static List<PostCommentResponse> getCommentResponses(Post post) {
        return post.getPostComments().stream().map(postComment -> PostCommentResponse.builder()
                .id(postComment.getId())
                .postId(postComment.getPost().getId())
                .loginId(post.getMember().getLoginId())
                .memberId(postComment.getMember().getId())
                .content(postComment.getContent())
                .createdDate(postComment.getCreatedDate())
                .build()).collect(Collectors.toList());
    }
}

package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.req.PostSearchCondition;
import com.ssafy.faraway.domain.post.dto.res.ListPostResponse;
import com.ssafy.faraway.domain.post.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {
    private final PostQueryRepository postQueryRepository;
    @Override
    public PostResponse searchById(Long postId) {
        Post post = postQueryRepository.searchById(postId);
        return PostResponse.builder()
                .id(post.getId())
                .memberId(post.getMember().getId())
                .loginId(post.getMember().getLoginId())
                .categoryName(post.getCategory().getCategoryName())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .build();
    }

    @Override
    public List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable) {
        return postQueryRepository.searchByCondition(condition, pageable);
    }
}

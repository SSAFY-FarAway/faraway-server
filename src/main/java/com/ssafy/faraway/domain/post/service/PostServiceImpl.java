package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.post.dto.PostResponse;
import com.ssafy.faraway.domain.post.dto.SavePostRequest;
import com.ssafy.faraway.domain.post.entity.Category;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Transactional
    @Override
    public Long save(SavePostRequest request, Long memberId) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(Category.builder().id(request.getCategoryId()).build())
                .member(Member.builder().id(memberId).build())
                .build();
        return postRepository.save(post).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public PostResponse searchById(Long postId) {
        Post post = postRepository.searchById(postId);
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
}

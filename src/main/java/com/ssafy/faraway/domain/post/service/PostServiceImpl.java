package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.post.dto.*;
import com.ssafy.faraway.domain.post.entity.Category;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Transactional
    @Override
    public Long update(Long postId, UpdatePostRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        post.update(request.getTitle(), request.getContent());
        return postId;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable) {
        return postRepository.searchByCondition(condition, pageable);
    }
}
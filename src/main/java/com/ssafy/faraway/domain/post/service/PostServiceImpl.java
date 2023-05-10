package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.member.entity.Role;
import com.ssafy.faraway.domain.member.repository.MemberRepository;
import com.ssafy.faraway.domain.post.dto.SavePostRequest;
import com.ssafy.faraway.domain.post.entity.Category;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
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
}

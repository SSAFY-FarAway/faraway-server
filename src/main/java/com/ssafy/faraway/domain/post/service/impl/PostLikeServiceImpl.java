package com.ssafy.faraway.domain.post.service.impl;

import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.entity.PostLike;
import com.ssafy.faraway.domain.post.repository.PostLikeRepository;
import com.ssafy.faraway.domain.post.service.PostLikeService;
import com.ssafy.faraway.domain.post.service.dto.SavePostLikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {
    private final PostLikeRepository postLikeRepository;
    @Override
    public Long save(SavePostLikeDto dto) {
        PostLike like = PostLike.builder()
                .member(Member.builder().id(dto.getMemberId()).build())
                .post(Post.builder().id(dto.getPostId()).build())
                .build();
        return postLikeRepository.save(like).getId();
    }
}

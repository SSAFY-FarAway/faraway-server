package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.entity.Post;

public interface PostCustomRepository {
    Post searchById(Long postId);
}

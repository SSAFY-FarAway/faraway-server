package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.SavePostRequest;

public interface PostService {
    Long save(SavePostRequest savePostRequest, Long memberId);
}

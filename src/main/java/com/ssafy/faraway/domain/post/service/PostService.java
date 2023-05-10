package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.PostResponse;
import com.ssafy.faraway.domain.post.dto.SavePostRequest;
import com.ssafy.faraway.domain.post.dto.UpdatePostRequest;

public interface PostService {
    Long save(SavePostRequest request, Long memberId);
    PostResponse searchById(Long postId);
    Long update(Long postId, UpdatePostRequest request);
}

package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.req.SavePostRequest;
import com.ssafy.faraway.domain.post.dto.req.UpdatePostRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostService {
    Long save(SavePostRequest request, Long memberId);

    Long update(Long postId, UpdatePostRequest request);

    Long delete(Long postId);
}

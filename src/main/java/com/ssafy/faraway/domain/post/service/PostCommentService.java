package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.req.SavePostCommentRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostCommentService {
    Long save(Long postId, Long memberId, SavePostCommentRequest request);
}

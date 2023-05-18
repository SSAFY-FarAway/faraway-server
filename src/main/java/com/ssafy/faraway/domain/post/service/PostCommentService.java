package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.controller.dto.req.SavePostCommentRequest;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostCommentService {
    Long save(Long postId, Long memberId, SavePostCommentRequest request);

    Long update(Long commentId, UpdatePostCommentDto dto);

    Long delete(Long commentId);
}

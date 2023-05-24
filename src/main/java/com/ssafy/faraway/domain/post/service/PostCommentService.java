package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.service.dto.SavePostCommentDto;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostCommentService {
    Long save(SavePostCommentDto dto);

    Long update(UpdatePostCommentDto dto);

    Long delete(Long commentId);
}

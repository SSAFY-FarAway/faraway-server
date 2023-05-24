package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.service.dto.SavePostLikeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostLikeService {
    Long save(SavePostLikeDto dto);

    Long delete(Long likeId);
}

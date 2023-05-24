package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.service.dto.SavePostDto;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostService {
    Long save(SavePostDto dto);

    Long update(UpdatePostDto dto);

    Long delete(Long postId);
}

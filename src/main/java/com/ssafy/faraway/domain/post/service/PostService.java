package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Long save(SavePostRequest request, Long memberId);
    PostResponse searchById(Long postId);
    Long update(Long postId, UpdatePostRequest request);
    List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable);
}

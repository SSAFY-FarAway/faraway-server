package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.dto.req.PostSearchCondition;
import com.ssafy.faraway.domain.post.dto.res.ListPostResponse;
import com.ssafy.faraway.domain.post.dto.res.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostQueryService {
    PostResponse searchById(Long postId);

    List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable);
}

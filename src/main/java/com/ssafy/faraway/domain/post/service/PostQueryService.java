package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.domain.post.repository.dto.PostSearchCondition;
import com.ssafy.faraway.domain.post.controller.dto.res.ListPostResponse;
import com.ssafy.faraway.domain.post.controller.dto.res.PostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostQueryService {
    PostResponse searchById(Long postId);

    List<ListPostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable);
    int getPageTotalCnt(PostSearchCondition condition);
}

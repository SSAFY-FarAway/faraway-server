package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.repository.dto.PostSearchCondition;
import com.ssafy.faraway.domain.post.controller.dto.res.PostResponse;
import com.ssafy.faraway.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryRepository {
    Post searchById(Long postId);

    List<PostResponse> searchByCondition(PostSearchCondition condition, Pageable pageable);

    List<PostResponse> searchByHit(PostSearchCondition condition, Pageable pageable);

    List<PostResponse> searchByLikeCnt(PostSearchCondition condition, Pageable pageable);

    int getPageTotalCnt(PostSearchCondition condition);
}

package com.ssafy.faraway.domain.post.repository;

import com.ssafy.faraway.domain.post.dto.req.PostSearchCondition;
import com.ssafy.faraway.domain.post.dto.res.ListPostResponse;
import com.ssafy.faraway.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryRepository {
    Post searchById(Long postId);
    List<ListPostResponse> searchByCondition(PostSearchCondition searchCondition, Pageable pageable);
}

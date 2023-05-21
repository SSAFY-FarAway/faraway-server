package com.ssafy.faraway.domain.post.repository;

public interface PostLikeQueryRepository {
    Long searchIdByCondition(Long memberId, Long postId);
}

package com.ssafy.faraway.domain.attraction.repository;

public interface AttractionLikeQueryRepository {
    Long searchLikeId(Long memberId, Integer contentId);
}

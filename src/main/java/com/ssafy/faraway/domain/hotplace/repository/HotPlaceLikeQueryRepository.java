package com.ssafy.faraway.domain.hotplace.repository;

public interface HotPlaceLikeQueryRepository {
    Long searchLikeId(Long memberId, Long hotPlaceId);
}

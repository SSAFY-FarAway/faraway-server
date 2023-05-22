package com.ssafy.faraway.domain.hotplace.service.impl;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.entity.HotPlaceLike;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceLikeRepository;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceLikeService;
import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceLikeDto;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceLikeServiceImpl implements HotPlaceLikeService {
    private final HotPlaceLikeRepository hotPlaceLikeRepository;

    @Override
    public Long save(SaveHotPlaceLikeDto dto) {
        HotPlaceLike like = HotPlaceLike.builder()
                .hotPlace(HotPlace.builder().id(dto.getHotPlaceId()).build())
                .member((Member.builder().id(dto.getMemberId()).build()))
                .build();
        return hotPlaceLikeRepository.save(like).getId();
    }

    @Override
    public Long delete(Long likeId) {
        HotPlaceLike like = hotPlaceLikeRepository.findById(likeId)
                .orElseThrow(() -> new CustomException(ErrorCode.HOT_PLACE_NOT_FOUND));
        hotPlaceLikeRepository.delete(like);
        return likeId;
    }
}

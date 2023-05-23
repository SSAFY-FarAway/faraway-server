package com.ssafy.faraway.domain.attraction.service.impl;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.attraction.entity.AttractionInfo;
import com.ssafy.faraway.domain.attraction.entity.AttractionLike;
import com.ssafy.faraway.domain.attraction.repository.AttractionLikeRepository;
import com.ssafy.faraway.domain.attraction.service.AttractionLikeService;
import com.ssafy.faraway.domain.attraction.service.dto.SaveAttractionLikeDto;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttractionLikeServiceImpl implements AttractionLikeService {
    private final AttractionLikeRepository attractionLikeRepository;

    @Override
    public Long save(SaveAttractionLikeDto dto) {
        AttractionLike like = AttractionLike.builder()
                .attractionInfo(AttractionInfo.builder().contentId(dto.getContentId()).build())
                .member(Member.builder().id(dto.getMemberId()).build())
                .build();
        return attractionLikeRepository.save(like).getId();
    }

    @Override
    public Long delete(Long likeId) {
        AttractionLike like = attractionLikeRepository.findById(likeId)
                .orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST));
        attractionLikeRepository.delete(like);
        return likeId;
    }
}

package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.dto.res.ListHotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {
    private final HotPlaceQueryRepository hotPlaceQueryRepository;

    @Override
    public HotPlaceResponse searchById(Long hotPlaceId) {
        HotPlace hotPlace = hotPlaceQueryRepository.searchById(hotPlaceId);
        return HotPlaceResponse.builder()
                .id(hotPlace.getId())
                .memberId(hotPlace.getMember().getId())
                .loginId(hotPlace.getMember().getLoginId())
                .title(hotPlace.getTitle())
                .content(hotPlace.getContent())
                .hit(hotPlace.getHit())
                .mainAddress(hotPlace.getAddress().getMainAddress())
                .subAddress(hotPlace.getAddress().getSubAddress())
                .rating(hotPlace.getRating())
                .createdDate(hotPlace.getCreatedDate())
                .build();
    }

    @Override
    public List<ListHotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        return hotPlaceQueryRepository.searchByCondition(condition, pageable);
    }
}

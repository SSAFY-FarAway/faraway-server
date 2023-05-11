package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.dto.res.HotPlaceCommentResponse;
import com.ssafy.faraway.domain.hotplace.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.dto.res.ListHotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {
    private final HotPlaceQueryRepository hotPlaceQueryRepository;

    @Override
    public HotPlaceResponse searchById(Long hotPlaceId) {
        HotPlace hotPlace = hotPlaceQueryRepository.searchById(hotPlaceId);
        List<HotPlaceCommentResponse> commentResponses = getCommentResponses(hotPlace);

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
                .commentResponses(commentResponses)
                .build();
    }

    private List<HotPlaceCommentResponse> getCommentResponses(HotPlace hotPlace) {
        return hotPlace.getHotPlaceComments().stream().map(hotPlaceComment -> HotPlaceCommentResponse.builder()
                .id(hotPlaceComment.getId())
                .hotPlaceId(hotPlaceComment.getHotPlace().getId())
                .loginId(hotPlace.getMember().getLoginId())
                .memberId(hotPlaceComment.getMember().getId())
                .content(hotPlace.getContent())
                .createdDate(hotPlace.getCreatedDate())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<ListHotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        return hotPlaceQueryRepository.searchByCondition(condition, pageable);
    }
}

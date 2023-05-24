package com.ssafy.faraway.domain.hotplace.service.impl;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceLikeQueryRepository;
import com.ssafy.faraway.domain.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.HotPlaceCommentResponse;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.HotPlaceImageResponse;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.DetailHotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.controller.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceQueryRepository;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.faraway.common.util.SizeConstants.PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {
    private final HotPlaceQueryRepository hotPlaceQueryRepository;
    private final HotPlaceLikeQueryRepository hotPlaceLikeQueryRepository;
    private final FileStore fileStore;

    @Transactional
    @Override
    public DetailHotPlaceResponse searchById(Long hotPlaceId, Long memberId) {
        HotPlace hotPlace = hotPlaceQueryRepository.searchById(hotPlaceId);
        List<HotPlaceCommentResponse> commentResponses = getCommentResponses(hotPlace);
        List<HotPlaceImageResponse> imageResponses = getImageResponses(hotPlace);
        if (!hotPlace.getMember().getId().equals(memberId)) {
            hotPlace.increaseHit();
        }
        return DetailHotPlaceResponse.builder()
                .id(hotPlace.getId())
                .memberId(hotPlace.getMember().getId())
                .loginId(hotPlace.getMember().getLoginId())
                .title(hotPlace.getTitle())
                .content(hotPlace.getContent())
                .hit(hotPlace.getHit())
                .likeCnt(hotPlace.getLikes().size())
                .likeId(hotPlaceLikeQueryRepository.searchLikeId(memberId, hotPlaceId))
                .zipcode(hotPlace.getAddress().getZipcode())
                .mainAddress(hotPlace.getAddress().getMainAddress())
                .subAddress(hotPlace.getAddress().getSubAddress())
                .rating(hotPlace.getRating())
                .createdDate(hotPlace.getCreatedDate())
                .commentResponses(commentResponses)
                .imageResponses(imageResponses)
                .build();
    }

    @Override
    public List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        List<HotPlace> hotPlaces = hotPlaceQueryRepository.searchByCondition(condition, pageable);
        List<HotPlaceResponse> responses = new ArrayList<>();
        for (HotPlace hotPlace : hotPlaces) {
            responses.add(HotPlaceResponse.builder()
                    .id(hotPlace.getId())
                    .memberId(hotPlace.getMember().getId())
                    .loginId(hotPlace.getMember().getLoginId())
                    .title(hotPlace.getTitle())
                    .content(hotPlace.getContent())
                    .hit(hotPlace.getHit())
                    .likeCnt(hotPlace.getLikes().size())
                    .likeId(hotPlaceLikeQueryRepository.searchLikeId(condition.getMemberId(), hotPlace.getId()))
                    .mainAddress(hotPlace.getAddress().getMainAddress())
                    .rating(hotPlace.getRating())
                    .thumbnailId(hotPlace.getImages().size() > 0 ? hotPlace.getImages().get(0).getId() : 0L)
                    .createdDate(hotPlace.getCreatedDate())
                    .build());
        }
        return responses;
    }

    @Override
    public int getPageTotalCnt(HotPlaceSearchCondition condition) {
        return ((hotPlaceQueryRepository.getPageTotalCnt(condition) - 1) / PAGE_SIZE) + 1;
    }

    private List<HotPlaceImageResponse> getImageResponses(HotPlace hotPlace) {
        return hotPlace.getImages().stream().map(hotPlaceImage -> HotPlaceImageResponse.builder()
                .id(hotPlaceImage.getId())
                .uploadFileName(fileStore.getFullPath(hotPlaceImage.getUploadFile().getUploadFileName()))
                .storeFileName(fileStore.getFullPath(hotPlaceImage.getUploadFile().getStoreFileName()))
                .createdDate(hotPlaceImage.getCreatedDate())
                .build()).collect(Collectors.toList());
    }

    private List<HotPlaceCommentResponse> getCommentResponses(HotPlace hotPlace) {
        return hotPlace.getComments().stream().map(hotPlaceComment -> HotPlaceCommentResponse.builder()
                .id(hotPlaceComment.getId())
                .hotPlaceId(hotPlaceComment.getHotPlace().getId())
                .loginId(hotPlaceComment.getMember().getLoginId())
                .memberId(hotPlaceComment.getMember().getId())
                .content(hotPlaceComment.getContent())
                .createdDate(hotPlaceComment.getCreatedDate())
                .build()).collect(Collectors.toList());
    }
}

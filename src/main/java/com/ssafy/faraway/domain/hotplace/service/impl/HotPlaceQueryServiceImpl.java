package com.ssafy.faraway.domain.hotplace.service.impl;

import com.ssafy.faraway.common.FileStore;
import com.ssafy.faraway.domain.hotplace.dto.req.HotPlaceSearchCondition;
import com.ssafy.faraway.domain.hotplace.dto.res.HotPlaceCommentResponse;
import com.ssafy.faraway.domain.hotplace.dto.res.HotPlaceImageResponse;
import com.ssafy.faraway.domain.hotplace.dto.res.HotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.dto.res.ListHotPlaceResponse;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceQueryRepository;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {
    private final HotPlaceQueryRepository hotPlaceQueryRepository;
    private final FileStore fileStore;

    @Transactional
    @Override
    public HotPlaceResponse searchById(Long hotPlaceId) {
        HotPlace hotPlace = hotPlaceQueryRepository.searchById(hotPlaceId);
        List<HotPlaceCommentResponse> commentResponses = getCommentResponses(hotPlace);
        List<HotPlaceImageResponse> imageResponses = getImageResponses(hotPlace);
        hotPlace.updateHit();
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
                .imageResponses(imageResponses)
                .build();
    }

    private List<HotPlaceImageResponse> getImageResponses(HotPlace hotPlace) {
        return hotPlace.getHotPlaceImages().stream().map(hotPlaceImage -> HotPlaceImageResponse.builder()
                .id(hotPlaceImage.getId())
                .uploadFileName(fileStore.getFullPath(hotPlaceImage.getUploadFile().getUploadFileName()))
                .storeFileName(fileStore.getFullPath(hotPlaceImage.getUploadFile().getStoreFileName()))
                .createdDate(hotPlaceImage.getCreatedDate())
                .build()).collect(Collectors.toList());
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

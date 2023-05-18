package com.ssafy.faraway.domain.hotplace.service.impl;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.hotplace.entity.Address;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.entity.HotPlaceImage;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceImageRepository;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceRepository;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceService;
import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceDto;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {
    private final HotPlaceRepository hotPlaceRepository;
    private final HotPlaceImageRepository hotPlaceImageRepository;

    @Override
    public Long save(Long memberId, SaveHotPlaceDto dto, List<UploadFile> uploadFiles) throws IOException {
        Address address = Address.builder()
                .zipcode(dto.getZipcode())
                .mainAddress(dto.getMainAddress())
                .subAddress(dto.getSubAddress())
                .build();


        HotPlace saveHotPlace = HotPlace.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .address(address)
                .rating(dto.getRating())
                .member(Member.builder().id(memberId).build())
                .build();

        Long saveId = hotPlaceRepository.save(saveHotPlace).getId();
        List<HotPlaceImage> hotPlaceImages = getImageList(saveId, uploadFiles);
        hotPlaceImageRepository.saveAll(hotPlaceImages);

        return saveId;
    }

    @Override
    public Long update(Long hotPlaceId, UpdateHotPlaceDto dto, List<UploadFile> uploadFiles) {
        HotPlace hotplace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(() -> new CustomException(ErrorCode.HOT_PLACE_NOT_FOUND));
        Address address = Address.builder()
                .zipcode(dto.getZipcode())
                .mainAddress(dto.getMainAddress())
                .subAddress(dto.getSubAddress())
                .build();
        List<HotPlaceImage> hotPlaceImages = getImageList(hotPlaceId, uploadFiles);
        hotplace.update(dto.getTitle(), dto.getContent(), address, dto.getRating(), hotPlaceImages);
        return hotPlaceId;
    }

    @Override
    public Long delete(Long hotPlaceId) {
        HotPlace hotplace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(() -> new CustomException(ErrorCode.HOT_PLACE_NOT_FOUND));
        hotPlaceRepository.delete(hotplace);
        return hotPlaceId;
    }

    private static List<HotPlaceImage> getImageList(Long saveId, List<UploadFile> uploadFiles) {
        if (uploadFiles.isEmpty()) {
            return new ArrayList<>();
        }
        return uploadFiles.stream()
                .map(uploadFile -> HotPlaceImage.builder()
                        .hotPlace(HotPlace.builder().id(saveId).build())
                        .uploadFile(uploadFile)
                        .build()).collect(Collectors.toList());
    }
}

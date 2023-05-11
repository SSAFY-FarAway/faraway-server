package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.dto.req.UpdateHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.entity.Address;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.entity.HotPlaceImage;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceRepository;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {
    private final HotPlaceRepository hotPlaceRepository;

    @Override
    public Long save(Long memberId, SaveHotPlaceRequest request, List<UploadFile> uploadFiles) throws IOException {
        Address address = Address.builder()
                .zipcode(request.getZipcode())
                .mainAddress(request.getMainAddress())
                .subAddress(request.getSubAddress())
                .build();

        List<HotPlaceImage> hotPlaceImages = new ArrayList<>();
        if (!uploadFiles.isEmpty()) {
            for (UploadFile uploadFile : uploadFiles) {
                HotPlaceImage hotPlaceImage = HotPlaceImage.builder()
                        .uploadFile(uploadFile)
                        .build();
                hotPlaceImages.add(hotPlaceImage);
            }
        }

        HotPlace saveHotPlace = HotPlace.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .address(address)
                .rating(request.getRating())
                .member(Member.builder().id(memberId).build())
                .hotPlaceImages(hotPlaceImages)
                .build();

        return hotPlaceRepository.save(saveHotPlace).getId();
    }

    @Override
    public Long update(Long hotPlaceId, UpdateHotPlaceRequest request) {
        HotPlace hotplace = hotPlaceRepository.findById(hotPlaceId).orElseThrow(NoSuchElementException::new);
        Address address = Address.builder()
                .zipcode(request.getZipcode())
                .mainAddress(request.getMainAddress())
                .subAddress(request.getSubAddress())
                .build();
        hotplace.update(request.getTitle(), request.getContent(), address, request.getRating());
        return hotPlaceId;
    }

    @Override
    public Long delete(Long hotPlaceId) {
        HotPlace hotplace = hotPlaceRepository.findById(hotPlaceId).orElseThrow(NoSuchElementException::new);
        hotPlaceRepository.delete(hotplace);
        return hotPlaceId;
    }
}

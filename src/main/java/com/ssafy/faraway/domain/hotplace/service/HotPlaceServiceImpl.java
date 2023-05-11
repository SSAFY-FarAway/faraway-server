package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.entity.Address;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceRepository;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {
    private final HotPlaceRepository hotPlaceRepository;

    @Override
    public Long save(Long memberId, SaveHotPlaceRequest request) {
        Address address = Address.builder()
                .zipcode(request.getZipcode())
                .mainAddress(request.getMainAddress())
                .subAddress(request.getSubAddress())
                .build();

        HotPlace saveHotPlace = HotPlace.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .address(address)
                .rating(request.getRating())
                .member(Member.builder().id(memberId).build())
                .build();

        return hotPlaceRepository.save(saveHotPlace).getId();
    }
}

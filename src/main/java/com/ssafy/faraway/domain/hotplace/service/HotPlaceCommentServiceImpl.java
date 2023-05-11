package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceCommentRequest;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.entity.HotPlaceComment;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceCommentServiceImpl implements HotPlaceCommentService {
    private final HotPlaceCommentRepository hotPlaceCommentRepository;

    @Override
    public Long save(Long hotPlaceId, Long memberId, SaveHotPlaceCommentRequest request) {
        HotPlaceComment hotPlaceComment = HotPlaceComment.builder()
                .content(request.getContent())
                .member(Member.builder().id(memberId).build())
                .hotPlace(HotPlace.builder().id(hotPlaceId).build())
                .build();
        return hotPlaceCommentRepository.save(hotPlaceComment).getId();
    }
}

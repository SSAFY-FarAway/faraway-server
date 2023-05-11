package com.ssafy.faraway.domain.hotplace.service;

import com.ssafy.faraway.domain.hotplace.dto.req.SaveHotPlaceCommentRequest;
import com.ssafy.faraway.domain.hotplace.dto.req.UpdateHotPlaceCommentRequest;
import com.ssafy.faraway.domain.hotplace.dto.req.UpdateHotPlaceRequest;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.entity.HotPlaceComment;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    @Override
    public Long update(Long commentId, UpdateHotPlaceCommentRequest request) {
        HotPlaceComment hotPlaceComment = hotPlaceCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        hotPlaceComment.update(request.getContent());
        return commentId;
    }

    @Override
    public Long delete(Long commentId) {
        HotPlaceComment hotPlaceComment = hotPlaceCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        hotPlaceCommentRepository.delete(hotPlaceComment);
        return commentId;
    }
}

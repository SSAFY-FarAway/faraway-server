package com.ssafy.faraway.domain.hotplace.service.impl;

import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.hotplace.entity.HotPlace;
import com.ssafy.faraway.domain.hotplace.entity.HotPlaceComment;
import com.ssafy.faraway.domain.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.faraway.domain.hotplace.service.HotPlaceCommentService;
import com.ssafy.faraway.domain.hotplace.service.dto.SaveHotPlaceCommentDto;
import com.ssafy.faraway.domain.hotplace.service.dto.UpdateHotPlaceCommentDto;
import com.ssafy.faraway.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class HotPlaceCommentServiceImpl implements HotPlaceCommentService {
    private final HotPlaceCommentRepository hotPlaceCommentRepository;

    @Override
    public Long save(Long hotPlaceId, Long memberId, SaveHotPlaceCommentDto dto) {
        HotPlaceComment hotPlaceComment = HotPlaceComment.builder()
                .content(dto.getContent())
                .member(Member.builder().id(memberId).build())
                .hotPlace(HotPlace.builder().id(hotPlaceId).build())
                .build();
        return hotPlaceCommentRepository.save(hotPlaceComment).getId();
    }

    @Override
    public Long update(Long commentId, UpdateHotPlaceCommentDto dto) {
        HotPlaceComment hotPlaceComment = hotPlaceCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        hotPlaceComment.update(dto.getContent());
        return commentId;
    }

    @Override
    public Long delete(Long commentId) {
        HotPlaceComment hotPlaceComment = hotPlaceCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        hotPlaceCommentRepository.delete(hotPlaceComment);
        return commentId;
    }
}

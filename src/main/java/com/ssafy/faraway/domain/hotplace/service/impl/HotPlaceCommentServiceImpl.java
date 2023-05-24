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

@Service
@RequiredArgsConstructor
public class HotPlaceCommentServiceImpl implements HotPlaceCommentService {
    private final HotPlaceCommentRepository hotPlaceCommentRepository;

    @Override
    public Long save(SaveHotPlaceCommentDto dto) {
        HotPlaceComment hotPlaceComment = HotPlaceComment.builder()
                .content(dto.getContent())
                .member(Member.builder().id(dto.getMemberId()).build())
                .hotPlace(HotPlace.builder().id(dto.getHotPlaceId()).build())
                .build();
        return hotPlaceCommentRepository.save(hotPlaceComment).getId();
    }

    @Override
    public Long update(UpdateHotPlaceCommentDto dto) {
        HotPlaceComment hotPlaceComment = hotPlaceCommentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        hotPlaceComment.update(dto.getContent());
        return hotPlaceComment.getId();
    }

    @Override
    public Long delete(Long commentId) {
        HotPlaceComment hotPlaceComment = hotPlaceCommentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        hotPlaceCommentRepository.delete(hotPlaceComment);
        return commentId;
    }
}

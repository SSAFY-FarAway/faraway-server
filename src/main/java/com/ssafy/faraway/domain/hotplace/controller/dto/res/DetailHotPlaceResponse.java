package com.ssafy.faraway.domain.hotplace.controller.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DetailHotPlaceResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String title;
    private String content;
    private int hit;
    private int likeCnt;
    private Long likeId;
    private String zipcode;
    private String mainAddress;
    private String subAddress;
    private int rating;
    private LocalDateTime createdDate;
    private List<HotPlaceCommentResponse> commentResponses;
    private List<HotPlaceImageResponse> imageResponses;

    @Builder
    public DetailHotPlaceResponse(Long id, Long memberId, String loginId, String title, String content, int hit, int likeCnt, Long likeId, String zipcode, String mainAddress, String subAddress, int rating, LocalDateTime createdDate, List<HotPlaceCommentResponse> commentResponses, List<HotPlaceImageResponse> imageResponses) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.likeCnt = likeCnt;
        this.likeId = likeId;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.rating = rating;
        this.createdDate = createdDate;
        this.commentResponses = commentResponses;
        this.imageResponses = imageResponses;
    }
}

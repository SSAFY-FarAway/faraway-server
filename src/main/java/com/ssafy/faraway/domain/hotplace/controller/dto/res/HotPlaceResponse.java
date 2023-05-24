package com.ssafy.faraway.domain.hotplace.controller.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HotPlaceResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String title;
    private String content;
    private int hit;
    private int likeCnt;
    private int commentCnt;
    private Long likeId;
    private String mainAddress;
    private int rating;
    private Long thumbnailId;
    private LocalDateTime createdDate;

    @Builder
    public HotPlaceResponse(Long id, Long memberId, String loginId, String title, String content, int hit, int likeCnt, int commentCnt, Long likeId, String mainAddress, int rating, Long thumbnailId, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
        this.likeId = likeId;
        this.mainAddress = mainAddress;
        this.rating = rating;
        this.thumbnailId = thumbnailId;
        this.createdDate = createdDate;
    }
}

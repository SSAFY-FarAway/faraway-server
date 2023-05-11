package com.ssafy.faraway.domain.hotplace.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ListHotPlaceResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String title;
    private String content;
    private int hit;
    private String mainAddress;
    private String subAddress;
    private int rating;
    private LocalDateTime createdDate;

    @Builder
    public ListHotPlaceResponse(Long id, Long memberId, String loginId, String title, String content, int hit, String mainAddress, String subAddress, int rating, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.rating = rating;
        this.createdDate = createdDate;
    }
}

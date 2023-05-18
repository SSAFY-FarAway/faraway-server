package com.ssafy.faraway.domain.hotplace.controller.dto.res;

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
    private int hit;
    private String mainAddress;
    private int rating;
    private LocalDateTime createdDate;

    @Builder
    public ListHotPlaceResponse(Long id, Long memberId, String loginId, String title, int hit, String mainAddress, int rating, LocalDateTime createdDate) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.title = title;
        this.hit = hit;
        this.mainAddress = mainAddress;
        this.rating = rating;
        this.createdDate = createdDate;
    }
}

package com.ssafy.faraway.domain.attraction.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttractionResponse {
    private int contentId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private double latitude;
    private double longitude;

    @Builder
    public AttractionResponse(int contentId, String title, String addr1, String addr2, String zipcode, String tel, String firstImage, double latitude, double longitude) {
        this.contentId = contentId;
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.tel = tel;
        this.firstImage = firstImage;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

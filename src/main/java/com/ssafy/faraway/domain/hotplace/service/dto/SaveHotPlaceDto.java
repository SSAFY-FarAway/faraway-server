package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveHotPlaceDto {
    private String title;
    private String content;
    private String zipcode;
    private String mainAddress;
    private String subAddress;
    private int rating;

    @Builder
    public SaveHotPlaceDto(String title, String content, String zipcode, String mainAddress, String subAddress, int rating) {
        this.title = title;
        this.content = content;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.rating = rating;
    }
}

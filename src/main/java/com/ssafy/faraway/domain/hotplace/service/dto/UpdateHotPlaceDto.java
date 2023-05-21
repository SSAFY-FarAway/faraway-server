package com.ssafy.faraway.domain.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateHotPlaceDto {
    private String title;
    private String content;
    private String zipcode;
    private String mainAddress;
    private String subAddress;
    private int rating;
    private List<Long> deleteImageIds;

    @Builder
    public UpdateHotPlaceDto(String title, String content, String zipcode, String mainAddress, String subAddress, int rating, List<Long> deleteImageIds) {
        this.title = title;
        this.content = content;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.rating = rating;
        this.deleteImageIds = deleteImageIds;
        if (this.deleteImageIds == null || this.deleteImageIds.isEmpty()) {
            this.deleteImageIds = new ArrayList<>();
        }
    }
}

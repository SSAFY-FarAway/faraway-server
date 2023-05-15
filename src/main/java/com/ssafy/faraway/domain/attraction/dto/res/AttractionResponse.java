package com.ssafy.faraway.domain.attraction.dto.res;

import lombok.Data;

@Data
public class AttractionResponse {
    private Long contentId;
    private String title;
    private String addr1;
    private String zipcode;
    private String tel;
    private String firstImage;
    private double latitude;
    private double longitude;
}

package com.ssafy.faraway.domain.hotplace.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateHotPlaceRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String mainAddress;
    @NotBlank
    private String subAddress;
    @NotNull
    private int rating;
}

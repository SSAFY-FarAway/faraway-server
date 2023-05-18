package com.ssafy.faraway.domain.hotplace.controller.dto.req;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UpdateHotPlaceRequest {
    @NotBlank
    @Size(max = 120)
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    @Size(max = 5)
    private String zipcode;
    @NotBlank
    private String mainAddress;
    @NotBlank
    private String subAddress;
    @NotNull
    @DecimalMax("5")
    private int rating;
}

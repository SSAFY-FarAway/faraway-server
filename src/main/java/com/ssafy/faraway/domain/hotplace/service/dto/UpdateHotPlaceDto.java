package com.ssafy.faraway.domain.hotplace.service.dto;

import com.ssafy.faraway.common.domain.UploadFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateHotPlaceDto {
    private Long hotPlaceId;
    private String title;
    private String content;
    private String zipcode;
    private String mainAddress;
    private String subAddress;
    private int rating;
    private List<Long> deleteImageIds;
    private List<UploadFile> uploadFiles;

    @Builder

    public UpdateHotPlaceDto(Long hotPlaceId, String title, String content, String zipcode, String mainAddress, String subAddress, int rating, List<Long> deleteImageIds, List<UploadFile> uploadFiles) {
        this.hotPlaceId = hotPlaceId;
        this.title = title;
        this.content = content;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.rating = rating;
        this.deleteImageIds = deleteImageIds == null ? new ArrayList<>() : deleteImageIds;
        this.uploadFiles = uploadFiles;
    }
}

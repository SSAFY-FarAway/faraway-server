package com.ssafy.faraway.domain.hotplace.service.dto;

import com.ssafy.faraway.common.domain.UploadFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SaveHotPlaceDto {
    private Long memberId;
    private String title;
    private String content;
    private String zipcode;
    private String mainAddress;
    private String subAddress;
    private int rating;
    private List<UploadFile> uploadFiles;

    @Builder
    public SaveHotPlaceDto(Long memberId, String title, String content, String zipcode, String mainAddress, String subAddress, int rating, List<UploadFile> uploadFiles) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.zipcode = zipcode;
        this.mainAddress = mainAddress;
        this.subAddress = subAddress;
        this.rating = rating;
        this.uploadFiles = uploadFiles;
    }
}

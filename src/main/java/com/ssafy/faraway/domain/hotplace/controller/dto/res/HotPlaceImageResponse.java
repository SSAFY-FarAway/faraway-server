package com.ssafy.faraway.domain.hotplace.controller.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HotPlaceImageResponse {
    private Long id;
    private String uploadFileName;
    private String storeFileName;
    private LocalDateTime createdDate;

    @Builder
    public HotPlaceImageResponse(Long id, String uploadFileName, String storeFileName, LocalDateTime createdDate) {
        this.id = id;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.createdDate = createdDate;
    }
}

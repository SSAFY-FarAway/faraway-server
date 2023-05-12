package com.ssafy.faraway.domain.hotplace.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HotPlaceImageResponse {
    private Long id;
    private String fileName;
    private LocalDateTime createdDate;

    @Builder
    public HotPlaceImageResponse(Long id, String fileName, LocalDateTime createdDate) {
        this.id = id;
        this.fileName = fileName;
        this.createdDate = createdDate;
    }
}

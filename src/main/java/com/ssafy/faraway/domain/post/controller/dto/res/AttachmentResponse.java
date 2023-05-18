package com.ssafy.faraway.domain.post.controller.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AttachmentResponse {
    private Long id;
    private String fileName;
    private LocalDateTime createdDate;

    @Builder
    public AttachmentResponse(Long id, String fileName, LocalDateTime createdDate) {
        this.id = id;
        this.fileName = fileName;
        this.createdDate = createdDate;
    }
}

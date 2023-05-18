package com.ssafy.faraway.domain.post.controller.dto.res;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DetailPostResponse {
    private Long id;
    private Long memberId;
    private String loginId;
    private String categoryName;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;
    private List<PostCommentResponse> postCommentResponses;
    private List<AttachmentResponse> attachmentResponses;

    @Builder
    public DetailPostResponse(Long id, Long memberId, String loginId, String categoryName, String title, String content, int hit, LocalDateTime createdDate, List<PostCommentResponse> postCommentResponses, List<AttachmentResponse> attachmentResponses) {
        this.id = id;
        this.memberId = memberId;
        this.loginId = loginId;
        this.categoryName = categoryName;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate;
        this.postCommentResponses = postCommentResponses;
        this.attachmentResponses = attachmentResponses;
    }
}

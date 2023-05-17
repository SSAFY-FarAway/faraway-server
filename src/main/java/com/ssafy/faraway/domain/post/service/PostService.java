package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.domain.post.dto.req.SavePostRequest;
import com.ssafy.faraway.domain.post.dto.req.UpdatePostRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostService {
    Long save(SavePostRequest request, Long memberId, List<UploadFile>uploadFiles);

    Long update(Long postId, UpdatePostRequest request, List<UploadFile> uploadFiles);

    Long delete(Long postId);
}

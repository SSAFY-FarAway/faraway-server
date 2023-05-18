package com.ssafy.faraway.domain.post.service;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.domain.post.service.dto.SavePostDto;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostService {
    Long save(SavePostDto dto, Long memberId, List<UploadFile>uploadFiles);

    Long update(Long postId, UpdatePostDto dto, List<UploadFile> uploadFiles);

    Long delete(Long postId);
}

package com.ssafy.faraway.domain.post.service.impl;

import com.ssafy.faraway.common.domain.UploadFile;
import com.ssafy.faraway.common.exception.entity.CustomException;
import com.ssafy.faraway.common.exception.entity.ErrorCode;
import com.ssafy.faraway.domain.member.entity.Member;
import com.ssafy.faraway.domain.post.entity.Attachment;
import com.ssafy.faraway.domain.post.entity.Category;
import com.ssafy.faraway.domain.post.entity.Post;
import com.ssafy.faraway.domain.post.repository.AttachmentRepository;
import com.ssafy.faraway.domain.post.repository.PostRepository;
import com.ssafy.faraway.domain.post.service.PostService;
import com.ssafy.faraway.domain.post.service.dto.SavePostDto;
import com.ssafy.faraway.domain.post.service.dto.UpdatePostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public Long save(SavePostDto dto) {
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(Category.builder().id(dto.getCategoryId()).build())
                .member(Member.builder().id(dto.getMemberId()).build())
                .build();
        Long saveId = postRepository.save(post).getId();

        List<Attachment> attachments = getAttachments(saveId, dto.getUploadFiles());
        attachmentRepository.saveAll(attachments);
        return saveId;
    }

    @Override
    public Long update(UpdatePostDto dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        List<Attachment> attachments = getAttachments(dto.getPostId(), dto.getUploadFiles());
        post.update(dto.getTitle(), dto.getContent(), attachments, dto.getDeleteAttachmentIds());
        return post.getId();
    }

    @Override
    public Long delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        postRepository.delete(post);
        return postId;
    }

    private List<Attachment> getAttachments(Long postId, List<UploadFile> uploadFiles) {
        if (uploadFiles.isEmpty()) {
            return new ArrayList<>();
        }
        return uploadFiles.stream()
                .map(uploadFile -> Attachment.builder()
                        .post(Post.builder().id(postId).build())
                        .uploadFile(uploadFile).build()
                )
                .collect(Collectors.toList());
    }
}
